package com.nopossible.entity.api;

import com.nopossible.entity.HttpUploadService;
import com.nopossible.entity.beans.UpLoadImagebean;
import com.nopossible.http.Api.BaseApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import rx.Observable;

public class UpLoadImageApi extends BaseApi<UpLoadImagebean> {


    /*需要上传的文件*/
    private MultipartBody.Part part;

    public UpLoadImageApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setBaseUrl("http://121.43.169.100:8099/v1/");
    }

    public void setPart(MultipartBody.Part part) {
        this.part = part;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpUploadService service = retrofit.create(HttpUploadService.class);
        return service.uploadImage(part);
    }
}
