package com.nopossible.activities.findpass;

import android.content.Context;

import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FindpassContract {
    interface View extends BaseView {
        void setTimer(int leftTime);
        void nextUi();
        void findSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
