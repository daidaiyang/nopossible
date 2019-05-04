package com.nopossible.activities.myaddress.addresslist;

import android.content.Context;

import com.nopossible.entity.beans.MyAddressListBean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresslistContract {
    interface View extends BaseView {
        void setListData(List<MyAddressListBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
