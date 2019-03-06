package com.nopossible.entity.api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.ygs.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.ygs.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import retrofit2.Retrofit;
import rx.Observable;

public class UserEnrollApi extends BaseApi {




    public UserEnrollApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }




    @Override
    public Observable getObservable(Retrofit retrofit) {
        return null;
    }
}
