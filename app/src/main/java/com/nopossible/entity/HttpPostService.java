package com.nopossible.entity;

import com.ygs.rxretrofitlibrary.retrofit_rx.Api.BaseResultEntity;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface HttpPostService {

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/user-login")
    Observable<BaseResultEntity<String>> userLogin(@Body RequestBody body);
}
