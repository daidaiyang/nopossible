package com.nopossible.activities.myapply;

import android.content.Context;

import com.nopossible.entity.api.GetProductKindListApi;
import com.nopossible.entity.api.ProductSubscribeaApi;
import com.nopossible.entity.beans.ProductKindBean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyapplyPresenter extends BasePresenterImpl<MyapplyContract.View> implements MyapplyContract.Presenter{



    public void getProductKindList(){
        GetProductKindListApi getProductKindListApi = new GetProductKindListApi(getKindList,mView.getThis());
        mView.getManager().doHttpDeal(getProductKindListApi);
    }


    public void  commitApply(String kindCode,String kindName ){
        ProductSubscribeaApi subscribeaApi = new ProductSubscribeaApi(commit,mView.getThis());

        mView.getManager().doHttpDeal(subscribeaApi);

    }




    private HttpOnNextListener<String> commit = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };


    private HttpOnNextListener<List<ProductKindBean>> getKindList = new HttpOnNextListener<List<ProductKindBean>>() {
        @Override
        public void onNext(List<ProductKindBean> list) {
            mView.showSelect(list);
        }
    };
}
