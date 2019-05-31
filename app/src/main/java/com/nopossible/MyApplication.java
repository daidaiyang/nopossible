package com.nopossible;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.nopossible.utils.AppUtil;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class MyApplication extends Application {


    public static Context app;
    private static boolean debug;

    private IWXAPI iwxapi;


    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        debug = BuildConfig.DEBUG;
        SpeechUtility.createUtility(app,SpeechConstant.APPID+"=5c834d65");
        regToWx();
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        iwxapi = WXAPIFactory.createWXAPI(this, AppUtil.APP_ID, true);

        // 将应用的appId注册到微信
        iwxapi.registerApp(AppUtil.APP_ID);
    }


    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        MyApplication.debug = debug;
    }
}
