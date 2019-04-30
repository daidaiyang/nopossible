package com.nopossible.activities.minegroup.myinfo;

import android.content.Context;

import com.nopossible.entity.beans.UpLoadImagebean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyinfoContract {
    interface View extends BaseView {
        void setSuccessImg(UpLoadImagebean bean);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
