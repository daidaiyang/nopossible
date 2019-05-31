package com.nopossible.activities.minegroup.myscore;

import android.content.Context;

import com.nopossible.entity.api.IntegralListApi;
import com.nopossible.entity.api.IntegralSumApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyscorePresenter extends BasePresenterImpl<MyscoreContract.View> implements MyscoreContract.Presenter{



    public void getIntegralList(){
        IntegralListApi integralListApi = new IntegralListApi(getList,mView.getThis());
        mView.getManager().doHttpDeal(integralListApi);
    }


    public void getIntegralSum(){
        IntegralSumApi integralSumApi = new IntegralSumApi(getSun,mView.getThis());
        mView.getManager().doHttpDeal(integralSumApi);
    }



    private HttpOnNextListener<String> getList = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };


    private HttpOnNextListener<String> getSun = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };
}
