package com.nopossible.activities.main.mine;

import com.nopossible.entity.api.GetUserDetailApi;
import com.nopossible.entity.beans.UserDetail;
import com.nopossible.entity.beans.UserLoginData;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter{


    public void getInfo(){
        GetUserDetailApi getUserDetailApi = new GetUserDetailApi(getUser,mView.getThis());
        mView.getManager().doHttpDeal(getUserDetailApi);
    }


    private HttpOnNextListener<UserLoginData> getUser = new HttpOnNextListener<UserLoginData>() {
        @Override
        public void onNext(UserLoginData userLoginData) {
            mView.setUserData(userLoginData);
        }
    };
}
