package com.nopossible.activities.main;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{


    public void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            new RxPermissions(mView.getThis()).requestEach(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.LOCATION_HARDWARE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                            if (permission.granted){//同意后调用

                            }else if (permission.shouldShowRequestPermissionRationale){//禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
//                                mView.exitApp();
                            }else {//禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
//                                mView.exitApp();
                            }
                        }
                    });
        }
    }
}
