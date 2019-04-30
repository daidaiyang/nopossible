package com.nopossible.activities.minegroup.myinfo;

import com.nopossible.entity.api.UpLoadImageApi;
import com.nopossible.entity.beans.UpLoadImagebean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyinfoPresenter extends BasePresenterImpl<MyinfoContract.View> implements MyinfoContract.Presenter{


    public void upDataImage(String path){
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        UpLoadImageApi upLoadImageApi = new UpLoadImageApi(upImage,mView.getThis());
        upLoadImageApi.setPart(part);
        mView.getManager().doHttpDeal(upLoadImageApi);
    }

    private HttpOnNextListener<UpLoadImagebean> upImage = new HttpOnNextListener<UpLoadImagebean>() {
        @Override
        public void onNext(UpLoadImagebean bean) {
            mView.setSuccessImg(bean);
        }
    };
}
