package com.nopossible.activities.register;

import android.content.Context;

import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterContract {
    interface View extends BaseView {
        void setTimer(int leftTime);
        void nextUi();
        void registerSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
