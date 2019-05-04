package com.nopossible.entity;

import com.nopossible.entity.beans.BasePageBean;
import com.nopossible.entity.beans.MyAddressListBean;
import com.nopossible.entity.beans.MyApplyBean;
import com.nopossible.entity.beans.PrivinceBean;
import com.nopossible.entity.beans.ProductKindBean;
import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.entity.beans.ShopCarProductBean;
import com.nopossible.entity.beans.UserLoginData;
import com.nopossible.http.Api.BaseResultEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface HttpGetService {

    //产品列表
    @GET("product/product-list")
    Observable<BaseResultEntity<BasePageBean<ProductListBean>>> productList(@QueryMap Map<String,Object> map);

    //购物车列表
    @GET("shop-car/shop-car-list")
    Observable<BaseResultEntity<BasePageBean<ShopCarProductBean>>> shopcarList(@QueryMap Map<String,Object> map);
    //获取用户信息
    @GET("user/user-detail")
    Observable<BaseResultEntity<UserLoginData>> getUserDetail();
    //获取省市区树
    @GET("basic/city/city-tree")
    Observable<BaseResultEntity<List<PrivinceBean>>> getCityTree();
    //获取用户常用地址列表
    @GET("user/common-location-list")
    Observable<BaseResultEntity<List<MyAddressListBean>>> getAddressList();
    //我的申购列表
    @GET("product/my-subscribe-list")
    Observable<BaseResultEntity<BasePageBean<MyApplyBean>>> mySubscribeList(@QueryMap Map<String,Object> map);
    //获取产品类型列表
    @GET("basic/product-kind-list")
    Observable<BaseResultEntity<List<ProductKindBean>>> getProductKindList();
    //获取符合拆单的商家列表   v1/user/merchant-user-list
    @GET("user/merchant-user-list")
    Observable<BaseResultEntity<List<String>>> getMerchantUserList();

    @GET("user/delete-location")
    Observable<BaseResultEntity<String>> deleteLocation(@QueryMap Map<String,Object> map);

}
