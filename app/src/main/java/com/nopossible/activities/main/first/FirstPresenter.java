package com.nopossible.activities.main.first;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechRecognizer;
import com.nopossible.dialog.RecognitionDialog;
import com.nopossible.entity.api.ProductListApi;
import com.nopossible.http.http.HttpManager;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;
import com.nopossible.utils.LogUtil;
import com.nopossible.utils.ToastUtil;
import com.nopossible.zxing.android.CaptureActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FirstPresenter extends BasePresenterImpl<FirstContract.View> implements FirstContract.Presenter{








    public void startScan() {
        final Intent intentScan = new Intent(mView.getContext(), CaptureActivity.class);
        intentScan.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            new RxPermissions(mView.getThis()).request(Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.LOCATION_HARDWARE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                mView.startScan(intentScan);
                            } else {
                                ToastUtil.showCenterToast(mView.getContext(), "需要使用相机权限，请在设置中允许后继续");
                            }
                        }
                    });
        } else {
          mView.startScan(intentScan);
        }
    }


    /**
     * 设置听写参数
     */
    public void setparam(SpeechRecognizer mIat){
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
        // 设置语言
        // 设置语言
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        // 设置语言区域
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        //此处用于设置dialog中不显示错误码信息
        //mIat.setParameter("view_tips_plain","false");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }
}
