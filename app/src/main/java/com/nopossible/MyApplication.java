package com.nopossible;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;


public class MyApplication extends Application {


    public static Context app;
    private static boolean debug;


    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        debug = BuildConfig.DEBUG;
        SpeechUtility.createUtility(app,SpeechConstant.APPID+"=5c834d65");
    }


    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        MyApplication.debug = debug;
    }
}
