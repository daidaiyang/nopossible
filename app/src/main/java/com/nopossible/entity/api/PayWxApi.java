package com.nopossible.entity.api;

import com.google.gson.Gson;
import com.nopossible.entity.HttpPostService;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class PayWxApi extends BaseApi {

    private RequestBody body;



    public PayWxApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

    }


    public void initData(String money,String[] order_no_list){
            body = getbody(new Gson().toJson(new Money(money,order_no_list)));
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.payWx(body);
    }


    private class Money{
        private String money;
        private String[] order_no_list;

        public Money(String money, String[] order_no_list) {
            this.money = money;
            this.order_no_list = order_no_list;
        }

        public String[] getOrder_no_list() {
            return order_no_list;
        }

        public void setOrder_no_list(String[] order_no_list) {
            this.order_no_list = order_no_list;
        }

        public Money(String money) {
            this.money = money;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

}
