package com.nopossible.entity.api;

import com.google.gson.Gson;
import com.nopossible.entity.HttpPostService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.ygs.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.ygs.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class SecurityCodeApi extends BaseApi {


    private SecurityBean securityBean;
    private RequestBody body;

    public SecurityCodeApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(String mobile,String code){
        securityBean = new SecurityBean(mobile,code);
        String json = new Gson().toJson(securityBean);
        body = getbody(json);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.SecurityCode(body);
    }

    class SecurityBean{
        private String phone;
        private String code;

        public SecurityBean(String phone, String code) {
            this.phone = phone;
            this.code = code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
