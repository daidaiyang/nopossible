package com.nopossible.activities.minegroup.myapply;

import android.content.Context;

import com.nopossible.entity.beans.MyApplyBean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyapplyContract {
    interface View extends BaseView {
        void setNoData();
        void setData(List<MyApplyBean> data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
