package com.nopossible.entity.api;

import com.nopossible.entity.HttpGetService;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;

public class DeleteLocationApi extends BaseApi {

    private Map<String,Object> map;

    public DeleteLocationApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void setId(String id) {
        map = new HashMap<>();
        map.put("id",id);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.deleteLocation(map);
    }
}
