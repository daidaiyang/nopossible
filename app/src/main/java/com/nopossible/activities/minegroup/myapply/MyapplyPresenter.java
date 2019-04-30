package com.nopossible.activities.minegroup.myapply;

import android.content.Context;

import com.nopossible.entity.api.MySubscribeListApi;
import com.nopossible.entity.beans.BasePageBean;
import com.nopossible.entity.beans.MyApplyBean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyapplyPresenter extends BasePresenterImpl<MyapplyContract.View> implements MyapplyContract.Presenter{


    private int page_num = 1;
    public void getList(){
        MySubscribeListApi mySubscribeListApi = new MySubscribeListApi(list,mView.getThis());
        mySubscribeListApi.initData(page_num);
        mView.getManager().doHttpDeal(mySubscribeListApi);
    }


    private HttpOnNextListener<BasePageBean<MyApplyBean>> list = new HttpOnNextListener<BasePageBean<MyApplyBean>>() {
        @Override
        public void onNext(BasePageBean<MyApplyBean> bean) {
            if (bean.getTotalPage() == 0){
                mView.setNoData();
            }else {
                mView.setData(bean.getData());
            }
        }
    };
}
