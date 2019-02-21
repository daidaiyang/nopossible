package com.nopossible.activities.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.register_username)
    EditText registerUsername;
    @BindView(R.id.register_code)
    EditText registerCode;
    @BindView(R.id.register_getcode)
    TextView registerGetcode;
    @BindView(R.id.register_next)
    TextView registerNext;
    @BindView(R.id.register_checked)
    CheckBox registerChecked;
    @BindView(R.id.register_register_agreement)
    TextView registerRegisterAgreement;
    @BindView(R.id.register_secret_agreement)
    TextView registerSecretAgreement;
    @BindView(R.id.register_agreement_ll)
    LinearLayout registerAgreementLl;
    @BindView(R.id.register_step1)
    LinearLayout registerStep1;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_seepassword)
    CheckBox registerSeepassword;
    @BindView(R.id.register_input_password)
    LinearLayout registerInputPassword;
    @BindView(R.id.register_password_again)
    EditText registerPasswordAgain;
    @BindView(R.id.register_seepassword_again)
    CheckBox registerSeepasswordAgain;
    @BindView(R.id.register_input_password_again)
    LinearLayout registerInputPasswordAgain;
    @BindView(R.id.register_step2)
    LinearLayout registerStep2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("注册");
        titleRight.setVisibility(View.GONE);
        registerNext.setText("下一步");
    }

    @OnClick({R.id.title_back, R.id.register_getcode, R.id.register_next, R.id.register_checked, R.id.register_register_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                if (registerNext.getText().toString().equals("注册")){
                    registerStep1.setVisibility(View.VISIBLE);
                    registerStep2.setVisibility(View.GONE);
                    registerNext.setText("下一步");
                }else{
                    RegisterActivity.this.finish();
                }
                break;
            case R.id.register_getcode:
                break;
            case R.id.register_next:
                registerStep1.setVisibility(View.GONE);
                registerStep2.setVisibility(View.VISIBLE);
                registerNext.setText("注册");
                break;
            case R.id.register_checked:
                break;
            case R.id.register_register_agreement:
                break;
        }
    }
}
