package com.nopossible.activities.main;

import android.content.Context;

import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseView {
        void exitApp();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
