package com.nopossible.activities.myapply;

import android.content.Context;

import com.nopossible.entity.beans.ProductKindBean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyapplyContract {
    interface View extends BaseView {
        void showSelect(List<ProductKindBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
