package com.nopossible.entity;

import com.ygs.rxretrofitlibrary.retrofit_rx.Api.BaseResultEntity;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface HttpPostService {

    @FormUrlEncoded
    @POST("user/user-login")
    Observable<BaseResultEntity<String>> userLogin(@FieldMap Map<String,String> map);
}
