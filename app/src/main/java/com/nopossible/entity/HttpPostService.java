package com.nopossible.entity;

import com.nopossible.entity.beans.UserLoginData;
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

    //登陆
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/user-login")
    Observable<BaseResultEntity<UserLoginData>> userLogin(@Body RequestBody body);
    //注册
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/user-enroll")
    Observable<BaseResultEntity<String>> userEnroll(@Body RequestBody body);
    //获取短信验证码
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/send-msg")
    Observable<BaseResultEntity<String>> sendMsg(@Body RequestBody body);
    //验证短信验证码
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/security-code")
    Observable<BaseResultEntity<String>> SecurityCode(@Body RequestBody body);
}
