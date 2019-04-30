package com.nopossible.activities.myaddress.addressedit;

import android.content.Context;

import com.nopossible.entity.beans.PrivinceBean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddresseditContract {
    interface View extends BaseView {
        void setAreaData(List<PrivinceBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
