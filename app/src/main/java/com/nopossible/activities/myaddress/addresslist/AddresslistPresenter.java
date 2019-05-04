package com.nopossible.activities.myaddress.addresslist;

import android.content.Context;

import com.nopossible.entity.api.DeleteLocationApi;
import com.nopossible.entity.api.GetAddressListApi;
import com.nopossible.entity.api.ModifyDefaultLocationApi;
import com.nopossible.entity.beans.MyAddressListBean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresslistPresenter extends BasePresenterImpl<AddresslistContract.View> implements AddresslistContract.Presenter{



    public void getAddressList(){
        GetAddressListApi getListApi = new GetAddressListApi(getList,mView.getThis());
        mView.getManager().doHttpDeal(getListApi);
    }

    public void setMoren(String id){
        ModifyDefaultLocationApi defaultLocationApi = new ModifyDefaultLocationApi(moren,mView.getThis());
        defaultLocationApi.initData(id);
        mView.getManager().doHttpDeal(defaultLocationApi);
    }

    public void delete(String id){
        DeleteLocationApi deleteLocationApi = new DeleteLocationApi(delete,mView.getThis());
        deleteLocationApi.setId(id);
        mView.getManager().doHttpDeal(deleteLocationApi);
    }


    private HttpOnNextListener<String> delete = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            getAddressList();
        }
    };

    private HttpOnNextListener<String> moren = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            getAddressList();
        }
    };



    private HttpOnNextListener<List<MyAddressListBean>> getList = new HttpOnNextListener<List<MyAddressListBean>>() {
        @Override
        public void onNext(List<MyAddressListBean> list) {
            mView.setListData(list);
        }
    };
}
