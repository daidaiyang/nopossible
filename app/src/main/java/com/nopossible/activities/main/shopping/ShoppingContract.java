package com.nopossible.activities.main.shopping;

import android.content.Context;

import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.entity.beans.ShopCarProductBean;
import com.nopossible.mvp.BasePresenter;
import com.nopossible.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ShoppingContract {
    interface View extends BaseView {
        void initData(List<ShopCarProductBean> data);
        void refreshData(List<ShopCarProductBean> data);
        void loadData(List<ShopCarProductBean> data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
