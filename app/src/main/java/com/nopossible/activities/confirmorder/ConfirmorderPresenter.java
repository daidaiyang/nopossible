package com.nopossible.activities.confirmorder;

import android.content.Context;

import com.nopossible.entity.api.CreateShopCarOrderApi;
import com.nopossible.entity.api.GetMerchantUserListApi;
import com.nopossible.entity.api.PayWxApi;
import com.nopossible.entity.beans.CreateShopCarOrderBean;
import com.nopossible.entity.beans.MyAddressListBean;
import com.nopossible.entity.beans.OrderLine;
import com.nopossible.entity.beans.OrderLineBean;
import com.nopossible.entity.beans.OrderList;
import com.nopossible.entity.beans.SplitOrderResultBean;
import com.nopossible.entity.beans.SplitOrder_orderList;
import com.nopossible.entity.beans.WeChatPayBean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.AppUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ConfirmorderPresenter extends BasePresenterImpl<ConfirmorderContract.View> implements ConfirmorderContract.Presenter{


    private IWXAPI iwxapi; //微信支付api


    public void createOrder(MyAddressListBean bean, List<SplitOrder_orderList> mData){
        CreateShopCarOrderApi createShopCarOrderApi = new CreateShopCarOrderApi(create,mView.getThis());
        CreateShopCarOrderBean orderBean = new CreateShopCarOrderBean();
        orderBean.setAddress(bean.getAddress());
        orderBean.setProvince_name(bean.getProvince_name());
        orderBean.setProvince_no(bean.getProvince_no());
        orderBean.setCity_name(bean.getCity_name());
        orderBean.setCity_no(bean.getCity_no());
        orderBean.setDistrict_name(bean.getDistrict_name());
        orderBean.setDistrict_no(bean.getDistrict_no());
        orderBean.setLongitude(bean.getLongitude());
        orderBean.setLatitude(bean.getLatitude());
        List<OrderList> orderLists = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            OrderList orderList = new OrderList();
            SplitOrder_orderList list = mData.get(i);
            orderList.setDelivery_id(list.getDelivery_id());
            orderList.setNo(list.getNo());
            orderList.setTake_contacts(list.getTake_contacts());
            orderList.setTake_tel_phone(list.getTake_tel_phone());
            orderList.setDelivery_time("2019-02-05");
            List<OrderLineBean> order_line = list.getOrder_line();
            List<OrderLine> oList = new ArrayList<>();
            for (int j = 0; j < order_line.size(); j++) {
                OrderLineBean orderLineBean = order_line.get(j);
                OrderLine orderLine = new OrderLine();
                orderLine.setNum(orderLineBean.getNum());
                orderLine.setProduct_id(orderLineBean.getProduct_id());
                oList.add(orderLine);
            }
            orderList.setOrder_line(oList);
            orderLists.add(orderList);
        }
        orderBean.setOrder_list(orderLists);
        createShopCarOrderApi.initData(orderBean);
        mView.getManager().doHttpDeal(createShopCarOrderApi);
    }


    public void getMerchantList(){
        GetMerchantUserListApi getMerchantUserListApi = new GetMerchantUserListApi(getMerchant,mView.getThis());
        mView.getManager().doHttpDeal(getMerchantUserListApi);
    }



    public void payByWeChat(String money,String[] order_no_list){
        PayWxApi payWxApi = new PayWxApi(payWechat,mView.getThis());
        payWxApi.initData(AppUtil.get2xiaoshu(money),order_no_list);
        mView.getManager().doHttpDeal(payWxApi);

    }


    private void toWxPay(final WeChatPayBean bean){
        iwxapi = WXAPIFactory.createWXAPI(mView.getContext(), null); //初始化微信api
        iwxapi.registerApp(AppUtil.APP_ID); //注册appid  appid可以在开发平台获取

        /**
         "appid": "wx6a6e70d91a1f8987",
         "noncestr": "1557733260823",
         "package": "Sign=WXPay",
         "partnerid": "1309161001",
         "prepayid": "wx131541009470694d77c36a0c3054525296",
         "sign": "185ED7CD2F666311D6E0982697516094",
         "timestamp": "1557733260"
         */
        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = bean.getAppid();
                request.partnerId = bean.getPartnerid();
                request.prepayId =bean.getPrepayid();
                request.packageValue ="Sign=WXPay";
                request.nonceStr = bean.getNoncestr();
                request.timeStamp = bean.getTimestamp();
                request.sign = bean.getSign();
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private HttpOnNextListener<WeChatPayBean> payWechat = new HttpOnNextListener<WeChatPayBean>() {
        @Override
        public void onNext(WeChatPayBean bean) {
            toWxPay(bean);
        }
    };


    private HttpOnNextListener<SplitOrderResultBean> create = new HttpOnNextListener<SplitOrderResultBean>() {
        @Override
        public void onNext(SplitOrderResultBean bean) {
            mView.payOrder(bean);
        }
    };


    private HttpOnNextListener<String> getMerchant = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };
}
