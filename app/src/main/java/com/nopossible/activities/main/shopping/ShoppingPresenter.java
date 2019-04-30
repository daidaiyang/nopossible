package com.nopossible.activities.main.shopping;

import android.content.Context;

import com.nopossible.activities.confirmorder.ConfirmorderActivity;
import com.nopossible.entity.api.DeleteCartProductApi;
import com.nopossible.entity.api.ModifyCartNumApi;
import com.nopossible.entity.api.ShopcarListApi;
import com.nopossible.entity.api.SplitShopCarOrderApi;
import com.nopossible.entity.beans.BasePageBean;
import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.entity.beans.ShopCarProductBean;
import com.nopossible.entity.beans.SplitOrderResultBean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.IntentUtil;
import com.nopossible.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ShoppingPresenter extends BasePresenterImpl<ShoppingContract.View> implements ShoppingContract.Presenter{


    private int pageNum = 1;
    private int type = 0;

    /**
     * 首次获取数据
     */
    public void getShopCarData(){
        type =0;
        ShopcarListApi shopcarListApi = new ShopcarListApi(shopcarList,mView.getThis());
        shopcarListApi.initData(pageNum);
        mView.getManager().doHttpDeal(shopcarListApi);
    }
    /**
     * 刷新数据
     */
    public void freshData(){
        type =1;
        pageNum = 1;
        ShopcarListApi shopcarListApi = new ShopcarListApi(shopcarList,mView.getThis());
        shopcarListApi.initData(pageNum);
        mView.getManager().doHttpDeal(shopcarListApi);
    }
    /**
     * 加载数据
     */
    public void loadData(){
        type =2;
        pageNum++;
        ShopcarListApi shopcarListApi = new ShopcarListApi(shopcarList,mView.getThis());
        shopcarListApi.initData(pageNum);
        mView.getManager().doHttpDeal(shopcarListApi);
    }

    /**
     * 改变购物车数量
     * @param id
     * @param num
     */
    public void changeNum(String id,int num){
        ModifyCartNumApi modifyCartNumApi = new ModifyCartNumApi(changeNum,mView.getThis());
        modifyCartNumApi.initData(id,num);
        mView.getManager().doHttpDeal(modifyCartNumApi);
    }

    /**
     * 删除产品
     * @param product_ids
     */
    public void deleteProduct(List<String> product_ids){
        DeleteCartProductApi deleteCartProductApi = new DeleteCartProductApi(delete,mView.getThis());
        deleteCartProductApi.initData(product_ids);
        mView.getManager().doHttpDeal(deleteCartProductApi);
    }

    /**
     * 结算
     */
    public void countShopping(List<String> product_ids){
        SplitShopCarOrderApi splitShopCarOrderApi = new SplitShopCarOrderApi(splitOrder,mView.getThis());
        splitShopCarOrderApi.initData(product_ids);
        mView.getManager().doHttpDeal(splitShopCarOrderApi);
    }


    private HttpOnNextListener<SplitOrderResultBean> splitOrder = new HttpOnNextListener<SplitOrderResultBean>() {
        @Override
        public void onNext(SplitOrderResultBean bean) {
            EventBus.getDefault().postSticky(bean);
            IntentUtil.startActivity(mView.getContext(),ConfirmorderActivity.class);
        }
    };


    private HttpOnNextListener<String> delete = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            ToastUtil.showBottomToast("产品删除成功");
            getShopCarData();
        }
    };


    private HttpOnNextListener<String> changeNum = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            freshData();
        }
    };



    private HttpOnNextListener<BasePageBean<ShopCarProductBean>> shopcarList = new HttpOnNextListener<BasePageBean<ShopCarProductBean>>() {
        @Override
        public void onNext(BasePageBean<ShopCarProductBean> bean) {
            if (type == 0){
                mView.initData(bean.getData());
            }else if (type ==1){
                mView.refreshData(bean.getData());
            }else {
                if (pageNum > bean.getTotalPage()){
                    pageNum--;
                    ToastUtil.showBottomToast("没有更多数据了~~~");
                }
                mView.loadData(bean.getData());
            }

        }
    };
}
