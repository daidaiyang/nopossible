package com.nopossible.activities.gooddetail;

import android.content.Context;

import com.nopossible.entity.api.AddToShopCarApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.ToastUtil;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GooddetailPresenter extends BasePresenterImpl<GooddetailContract.View> implements GooddetailContract.Presenter{


    /**
     * 加入购物车
     */
    public void  addtocart(String productId){
        AddToShopCarApi addToShopCarApi = new AddToShopCarApi(addToShop,mView.getThis());
        addToShopCarApi.initData(productId,"1");
        mView.getManager().doHttpDeal(addToShopCarApi);
    }

    private HttpOnNextListener<String> addToShop = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {
            ToastUtil.showBottomToast("添加购物车成功");
        }
    };
}
