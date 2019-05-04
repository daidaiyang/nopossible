package com.nopossible.activities.confirmorder;

import android.content.Context;

import com.nopossible.entity.api.CreateShopCarOrderApi;
import com.nopossible.entity.api.GetMerchantUserListApi;
import com.nopossible.entity.beans.CreateShopCarOrderBean;
import com.nopossible.entity.beans.MyAddressListBean;
import com.nopossible.entity.beans.OrderLine;
import com.nopossible.entity.beans.OrderLineBean;
import com.nopossible.entity.beans.OrderList;
import com.nopossible.entity.beans.SplitOrderResultBean;
import com.nopossible.entity.beans.SplitOrder_orderList;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ConfirmorderPresenter extends BasePresenterImpl<ConfirmorderContract.View> implements ConfirmorderContract.Presenter{



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



    private HttpOnNextListener<SplitOrderResultBean> create = new HttpOnNextListener<SplitOrderResultBean>() {
        @Override
        public void onNext(SplitOrderResultBean bean) {
            mView.payOrder();
        }
    };


    private HttpOnNextListener<String> getMerchant = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };
}
