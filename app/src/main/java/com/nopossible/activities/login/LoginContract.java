package com.nopossible.activities.login;

import android.content.Context;

import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void loginSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
