package com.nopossible.activities.search;

import android.content.Context;
import android.util.Log;

import com.nopossible.entity.api.AddToShopCarApi;
import com.nopossible.entity.api.ProductListApi;
import com.nopossible.entity.beans.BasePageBean;
import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.LogUtil;
import com.nopossible.utils.ToastUtil;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SearchPresenter extends BasePresenterImpl<SearchContract.View> implements SearchContract.Presenter{


    private int pageNum = 1;

    /**
     * 首次搜商品
     * @param key
     */
    public void search(String key){
        ProductListApi productListApi = new ProductListApi(searchProduct,mView.getThis());
        productListApi.initData(key,pageNum);
        mView.getManager().doHttpDeal(productListApi);
    }

    /**
     *刷新商品
     * @param key
     */
    public void refresh(String key){
        pageNum = 1;
        ProductListApi productListApi = new ProductListApi(refreshProduct,mView.getThis());
        productListApi.initData(key,pageNum);
        mView.getManager().doHttpDeal(productListApi);
    }

    /**
     * 加载商品
     * @param key
     */
    public void load(String key){
        pageNum ++;
        ProductListApi productListApi = new ProductListApi(loadProduct,mView.getThis());
        productListApi.initData(key,pageNum);
        mView.getManager().doHttpDeal(productListApi);
    }

    /**
     * 加入购物车
     */
    public void  addtocart(String productId){
        AddToShopCarApi addToShopCarApi = new AddToShopCarApi(addToShop,mView.getThis());
        addToShopCarApi.initData(productId,"1");
        mView.getManager().doHttpDeal(addToShopCarApi);
    }





    /**
     * 第一次加载
     */
    private HttpOnNextListener<BasePageBean<ProductListBean>> searchProduct = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
             mView.upDataData(bean.getData());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e("error",e.getMessage());
        }
    };
    /**
     * 刷新
     */
    private HttpOnNextListener<BasePageBean<ProductListBean>> refreshProduct = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            mView.refreshData(bean.getData());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e("error",e.getMessage());
        }
    };
    /**
     * 加载
     */
    private HttpOnNextListener<BasePageBean<ProductListBean>> loadProduct = new HttpOnNextListener<BasePageBean<ProductListBean>>() {
        @Override
        public void onNext(BasePageBean<ProductListBean> bean) {
            if (pageNum>bean.getTotalPage()){
                pageNum--;
                ToastUtil.showBottomToast("没有更多数据了~~~");
            }
            mView.refreshData(bean.getData());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            LogUtil.e("error",e.getMessage());
        }
    };


    private HttpOnNextListener<String> addToShop = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            ToastUtil.showBottomToast("添加购物车成功");
        }
    };
}
