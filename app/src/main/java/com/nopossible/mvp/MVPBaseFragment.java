package com.nopossible.mvp;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.nopossible.http.http.HttpManager;
import com.nopossible.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;

import io.reactivex.functions.Consumer;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends Fragment implements BaseView{
    public T mPresenter;
    public HttpManager manager = HttpManager.getInstance();

    public RxPermissions rxPermissions;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
        rxPermissions = new RxPermissions(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detachView();
    }


    @Override
    public HttpManager getManager() {
        return manager;
    }

    @Override
    public RxAppCompatActivity getThis() {
        return (RxAppCompatActivity) getActivity();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    public  <T> T getInstance(Object o, int i) {
            try {
                return ((Class<T>) ((ParameterizedType) (o.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }
}
