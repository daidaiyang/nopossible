package com.nopossible.activities.login;

import android.content.Context;
import android.util.Log;

import com.nopossible.entity.api.UserLoginApi;
import com.nopossible.mvp.BasePresenterImpl;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{


    private HttpOnNextListener loginListener = new HttpOnNextListener<String>(){

        @Override
        public void onNext(String s) {
            Log.d("lgin",s);
        }
    };

    public void login(String name, String password){
        Map<String,String> map = new HashMap<>();
        map.put("phone",name);
        map.put("pwd",password);
        UserLoginApi userLoginApi = new UserLoginApi(loginListener,mView.getThis());
        userLoginApi.setParams(map);
        mView.getManager().doHttpDeal(userLoginApi);
    }
}
