package com.nopossible.activities.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.findpass.FindpassActivity;
import com.nopossible.activities.register.RegisterActivity;
import com.nopossible.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_login)
    TextView loginLogin;
    @BindView(R.id.login_regist)
    TextView loginRegist;
    @BindView(R.id.login_forgetpassword)
    TextView loginForgetpassword;
    @BindView(R.id.login_seepassword)
    CheckBox loginSeepassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("登录");
        titleRight.setVisibility(View.GONE);
    }

    @OnClick({R.id.title_back, R.id.login_login, R.id.login_regist, R.id.login_forgetpassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.login_login:
                break;
            case R.id.login_regist:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forgetpassword:
                Intent passIntent = new Intent(LoginActivity.this,FindpassActivity.class);
                startActivity(passIntent);
                break;
        }
    }
}
