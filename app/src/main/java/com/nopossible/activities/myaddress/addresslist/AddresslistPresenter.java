package com.nopossible.activities.myaddress.addresslist;

import android.content.Context;

import com.nopossible.entity.api.GetAddressListApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresslistPresenter extends BasePresenterImpl<AddresslistContract.View> implements AddresslistContract.Presenter{



    public void getAddressList(){
        GetAddressListApi getListApi = new GetAddressListApi(getList,mView.getThis());
        mView.getManager().doHttpDeal(getListApi);
    }



    private HttpOnNextListener<String> getList = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };
}
