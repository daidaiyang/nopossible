package com.nopossible.entity.api;

import com.nopossible.entity.HttpGetService;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import retrofit2.Retrofit;
import rx.Observable;

public class GetAddressListApi extends BaseApi {



    public GetAddressListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAddressList();
    }
}
