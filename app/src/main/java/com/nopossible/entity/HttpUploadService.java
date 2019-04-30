package com.nopossible.entity;

import com.nopossible.entity.beans.UpLoadImagebean;
import com.nopossible.http.Api.BaseResultEntity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface HttpUploadService {

    @Multipart
    @POST("file/upload")
    Observable<BaseResultEntity<UpLoadImagebean>> uploadImage(@Part MultipartBody.Part file);
}
