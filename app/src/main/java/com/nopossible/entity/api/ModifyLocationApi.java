package com.nopossible.entity.api;

import com.google.gson.Gson;
import com.nopossible.activities.myaddress.MyAddressEventBean;
import com.nopossible.entity.HttpPostService;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class ModifyLocationApi extends BaseApi {

    private RequestBody body;
    private MyAddressEventBean bean;

    public ModifyLocationApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(MyAddressEventBean bean){
        this.bean = bean;
        body = getbody(new Gson().toJson(bean));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.modifyLocation(body);
    }
}
