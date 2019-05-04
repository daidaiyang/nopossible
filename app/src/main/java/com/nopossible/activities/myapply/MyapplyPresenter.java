package com.nopossible.activities.myapply;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import com.nopossible.entity.api.GetProductKindListApi;
import com.nopossible.entity.api.ProductSubscribeaApi;
import com.nopossible.entity.api.UpLoadImageApi;
import com.nopossible.entity.beans.ProductKindBean;
import com.nopossible.entity.beans.ProductSubScribeBean;
import com.nopossible.entity.beans.UpLoadImagebean;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.BitmapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyapplyPresenter extends BasePresenterImpl<MyapplyContract.View> implements MyapplyContract.Presenter{


    private int imgPosition = 0;
    private int maxPosition = 0;

    private StringBuilder stringBuilder;

    private ProductSubScribeBean pBean;
    private List<String> paths;


    private ProgressDialog pd = null;
    private MultipartBody.Part part;

    public void getProductKindList(){
        GetProductKindListApi getProductKindListApi = new GetProductKindListApi(getKindList,mView.getThis());
        mView.getManager().doHttpDeal(getProductKindListApi);
    }


    public void upPic(ProductSubScribeBean bean, final List<String> filePath){
//        paths = new ArrayList<>();
//        if (pd == null){
//            pd = new ProgressDialog(mView.getContext());
//        }
//        pd.show();
        this.pBean = bean;
        stringBuilder = new StringBuilder();
        maxPosition = filePath.size();
        for (int i = 0; i < paths.size(); i++) {
            File file = new File(paths.get(i));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            UpLoadImageApi upLoadImageApi = new UpLoadImageApi(upImage,mView.getThis());
            upLoadImageApi.setPart(part);
            mView.getManager().doHttpDeal(upLoadImageApi);
        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < maxPosition; i++) {
//                    Bitmap map = BitmapUtils.getimage(filePath.get(i));
//                    String path = BitmapUtils.saveBitMap(map);
//                    paths.add(path);
//                }
//                for (int i = 0; i < paths.size(); i++) {
//                    File file = new File(paths.get(i));
//                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
//                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
//                    UpLoadImageApi upLoadImageApi = new UpLoadImageApi(upImage,mView.getThis());
//                    upLoadImageApi.setPart(part);
//                    mView.getManager().doHttpDeal(upLoadImageApi);
//                }
//            }
//        }).start();
    }




    private HttpOnNextListener<UpLoadImagebean> upImage = new HttpOnNextListener<UpLoadImagebean>() {
        @Override
        public void onNext(UpLoadImagebean bean) {
            imgPosition++;
            stringBuilder.append(bean.getUrl()+",");
            if (imgPosition == maxPosition){
                stringBuilder.substring(0,stringBuilder.length()-1);
            }
            pBean.setImgages(stringBuilder.toString());
//            mView.getThis().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
                    commitApply();
//                }
//            });

        }
    };


    public void  commitApply(){
        ProductSubscribeaApi subscribeaApi = new ProductSubscribeaApi(commit,mView.getThis());
        subscribeaApi.initData(pBean);
        mView.getManager().doHttpDeal(subscribeaApi);

    }




    private HttpOnNextListener<String> commit = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };


    private HttpOnNextListener<List<ProductKindBean>> getKindList = new HttpOnNextListener<List<ProductKindBean>>() {
        @Override
        public void onNext(List<ProductKindBean> list) {
            mView.showSelect(list);
        }
    };
}
