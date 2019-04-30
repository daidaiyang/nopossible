package com.nopossible.activities.changepassword;

import com.nopossible.entity.api.ModifyPwdByOldApi;
import com.nopossible.http.listener.HttpOnNextListener;
import com.nopossible.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangepasswordPresenter extends BasePresenterImpl<ChangepasswordContract.View> implements ChangepasswordContract.Presenter{



    public void changePass(String oldpass,String newpass){
        ModifyPwdByOldApi modifyPwdByOldApi = new ModifyPwdByOldApi(modify,mView.getThis());
        modifyPwdByOldApi.initData(oldpass,newpass);
        mView.getManager().doHttpDeal(modifyPwdByOldApi);
    }


    private HttpOnNextListener<String> modify = new HttpOnNextListener<String>() {
        @Override
        public void onNext(String s) {

        }
    };
}
