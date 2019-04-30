package com.nopossible.mvp;

import android.content.Context;

import com.nopossible.http.http.HttpManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface BaseView {
     Context getContext();
     HttpManager getManager();
     RxAppCompatActivity getThis();
}
