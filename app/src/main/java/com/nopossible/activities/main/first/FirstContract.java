package com.nopossible.activities.main.first;

import android.content.Context;
import android.content.Intent;

import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FirstContract {
    interface View extends BaseView {
        void startScan(Intent intent);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
