package com.nopossible.http.exception;

import com.nopossible.MyApplication;
import com.nopossible.activities.login.LoginActivity;
import com.nopossible.utils.IntentUtil;
import com.nopossible.utils.SpUtils;

/**
 * 自定义错误信息，统一处理返回处理
 */
public class HttpTimeException extends RuntimeException {

    public static final int NO_DATA = 0x2;

    public HttpTimeException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public HttpTimeException(String detailMessage) {
        super(detailMessage);
        if (detailMessage.contains("token已过期")){
            SpUtils.putString(MyApplication.app,"token","");
            IntentUtil.startActivity(MyApplication.app,LoginActivity.class);
        }
    }

    /**
     * 转换错误数据
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case NO_DATA:
                message = "无数据";
                break;
            default:
                message = "error";
                break;

        }
        return message;
    }
}

