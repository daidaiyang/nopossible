package com.nopossible.entity.api;

import com.google.gson.Gson;
import com.nopossible.entity.HttpPostService;
import com.nopossible.entity.beans.SplitOrderResultBean;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class SplitShopCarOrderApi extends BaseApi<SplitOrderResultBean> {

    private RequestBody body;

    public SplitShopCarOrderApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public void initData(List<String> ids){
        SplitShopCarOrderApiBean bean = new SplitShopCarOrderApiBean();
        List<SplitShopCarOrderApiBean2> list = new ArrayList<>();
        for (String product_id:ids) {
                list.add(new SplitShopCarOrderApiBean2(product_id));
        }
        bean.setOrder_line(list);
        body = getbody( new Gson().toJson(bean));

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.splitShopCarOrder(body);
    }


    private class SplitShopCarOrderApiBean{

        private List<SplitShopCarOrderApiBean2> order_line;

        public SplitShopCarOrderApiBean() {
        }

        public List<SplitShopCarOrderApiBean2> getOrder_line() {
            return order_line;
        }

        public void setOrder_line(List<SplitShopCarOrderApiBean2> order_line) {
            this.order_line = order_line;
        }

        public SplitShopCarOrderApiBean(List<SplitShopCarOrderApiBean2> order_line) {
            this.order_line = order_line;
        }
    }
    private class SplitShopCarOrderApiBean2{

        private String product_id;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public SplitShopCarOrderApiBean2(String product_id) {
            this.product_id = product_id;
        }
    }
}
