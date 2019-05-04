package com.nopossible.entity.api;

import com.google.gson.Gson;
import com.nopossible.entity.HttpPostService;
import com.nopossible.entity.beans.CreateShopCarOrderBean;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class CreateShopCarOrderApi extends BaseApi {


    private RequestBody body;


    public CreateShopCarOrderApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(CreateShopCarOrderBean bean){
        body = getbody(new Gson().toJson(bean));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.createShopCarOrder(body);
    }



}
