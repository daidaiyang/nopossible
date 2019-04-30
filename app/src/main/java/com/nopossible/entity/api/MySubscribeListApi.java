package com.nopossible.entity.api;

import com.nopossible.entity.HttpGetService;
import com.nopossible.entity.beans.BasePageBean;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class MySubscribeListApi extends BaseApi {


    private int page_size = 10;

    private Map<String,Object> map;

    public MySubscribeListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(int page_num){
        map = new HashMap<>();
        map.put("page_size",page_size);
        map.put("page_num",page_num);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService =  retrofit.create(HttpGetService.class);
        return httpGetService.mySubscribeList(map);
    }
}
