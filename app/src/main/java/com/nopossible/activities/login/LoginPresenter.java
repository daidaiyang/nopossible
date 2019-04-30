package com.nopossible.activities.login;

import android.util.Log;

import com.nopossible.entity.api.UserLoginApi;
import com.nopossible.entity.beans.UserLoginData;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.LogUtil;
import com.nopossible.utils.SpUtils;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{


    public void login(String name, String password){
        UserLoginApi userLoginApi = new UserLoginApi(loginListener,mView.getThis());
        userLoginApi.setParams(name,password);
        mView.getManager().doHttpDeal(userLoginApi);
    }

    private HttpOnNextListener loginListener = new HttpOnNextListener<UserLoginData>(){

        @Override
        public void onNext(UserLoginData userLoginData) {
            LogUtil.d("UserLoginData",userLoginData.getUser().getToken());
            SpUtils.putString(mView.getContext(),"token",userLoginData.getUser().getToken());
            mView.loginSuccess();
        }
    };
}
