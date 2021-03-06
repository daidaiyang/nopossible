package com.nopossible.activities.main.mine;

import android.content.Context;

import com.nopossible.entity.beans.UserDetail;
import com.nopossible.entity.beans.UserLoginData;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MineContract {
    interface View extends BaseView {
        void setUserData(UserLoginData userLoginData);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
