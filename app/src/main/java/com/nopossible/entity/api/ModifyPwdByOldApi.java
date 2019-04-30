package com.nopossible.entity.api;

import com.google.gson.Gson;
import com.nopossible.entity.HttpPostService;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class ModifyPwdByOldApi extends BaseApi<String> {


    private RequestBody body;

    public ModifyPwdByOldApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(String old,String news){
        body = getbody(new Gson().toJson(new ModifyPwdByOldBean(old, news)));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.modifyPwdByOld(body);
    }

    private class ModifyPwdByOldBean{
        private String ngve;
        private String plus;

        public ModifyPwdByOldBean(String ngve, String plus) {
            this.ngve = ngve;
            this.plus = plus;
        }

        public String getNgve() {
            return ngve;
        }

        public void setNgve(String ngve) {
            this.ngve = ngve;
        }

        public String getPlus() {
            return plus;
        }

        public void setPlus(String plus) {
            this.plus = plus;
        }
    }
}
