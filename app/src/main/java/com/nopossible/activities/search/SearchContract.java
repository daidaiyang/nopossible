package com.nopossible.activities.search;

import android.content.Context;

import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SearchContract {
    interface View extends BaseView {
        void upDataData(List<ProductListBean> mData);
        void refreshData(List<ProductListBean> mData);
        void loadData(List<ProductListBean> mData);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
