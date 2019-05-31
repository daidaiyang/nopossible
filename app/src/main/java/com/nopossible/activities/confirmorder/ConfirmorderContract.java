package com.nopossible.activities.confirmorder;

import android.content.Context;

import com.nopossible.entity.beans.SplitOrderResultBean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ConfirmorderContract {
    interface View extends BaseView {
        void payOrder(SplitOrderResultBean bean);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
