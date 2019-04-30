package com.nopossible.activities.myaddress.addressedit;

import android.content.Context;

import com.nopossible.activities.myaddress.MyAddressEventBean;
import com.nopossible.entity.api.GetCityTreeApi;
import com.nopossible.entity.api.ModifyLocationApi;
import com.nopossible.entity.beans.PrivinceBean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresseditPresenter extends BasePresenterImpl<AddresseditContract.View> implements AddresseditContract.Presenter{



    public void getAreaInfo(){
        GetCityTreeApi getCityTreeApi = new GetCityTreeApi(getCityTree,mView.getThis());
        mView.getManager().doHttpDeal(getCityTreeApi);
    }


    public void editArea(MyAddressEventBean bean){
        ModifyLocationApi modifyLocationApi = new ModifyLocationApi(modify,mView.getThis());
        modifyLocationApi.initData(bean);
        mView.getManager().doHttpDeal(modifyLocationApi);
    }



    private HttpOnNextListener<String> modify = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };


    private HttpOnNextListener<List<PrivinceBean>> getCityTree = new HttpOnNextListener<List<PrivinceBean>>() {
        @Override
        public void onNext(List<PrivinceBean> list) {
            mView.setAreaData(list);
        }
    };
}
