package com.nopossible.entity.api;

import com.nopossible.entity.HttpPostService;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class ProductSubscribeaApi extends BaseApi {



    private RequestBody body;

    public ProductSubscribeaApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }


    public void initData(){

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.productSubscribe(body);
    }
}
