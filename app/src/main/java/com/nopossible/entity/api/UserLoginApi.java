package com.nopossible.entity.api;

import com.nopossible.entity.HttpPostService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.ygs.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.ygs.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

public class UserLoginApi extends BaseApi {


    private Map<String,String> params;


    public UserLoginApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCancel(true);
        setCache(true);
        setMethod("v1/user-user-login");
//        setCookieNetWorkTime(60);
//        setCookieNoNetWorkTime(24*60*60);
    }


    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.userLogin(params);
    }

}
