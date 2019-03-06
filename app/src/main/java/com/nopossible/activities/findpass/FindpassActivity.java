package com.nopossible.activities.findpass;


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
 */

public class FindpassActivity extends MVPBaseActivity<FindpassContract.View, FindpassPresenter> implements FindpassContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.findpass_step1_username)
    EditText step1Username;
    @BindView(R.id.findpass_step1_code)
    EditText step1Code;
    @BindView(R.id.findpass_step1_getcode)
    TextView step1Getcode;
    @BindView(R.id.findpass_step1)
    LinearLayout step1;
    @BindView(R.id.findpass_step2_pass)
    EditText step2Pass;
    @BindView(R.id.findpass_step2_pass_cansee)
    CheckBox step2PassCansee;
    @BindView(R.id.findpass_step2_pass_again)
    EditText step2PassAgain;
    @BindView(R.id.findpass_step2_pass_cansee_again)
    CheckBox sStep2PassCanseeAgain;
    @BindView(R.id.findpass_step2)
    LinearLayout step2;
    @BindView(R.id.findpass_next)
    TextView findpassNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpass);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        titleTxt.setText("找回密码");
        titleRight.setVisibility(View.GONE);
    }

    @OnClick({R.id.title_back, R.id.findpass_step1_getcode, R.id.findpass_step2_pass_cansee_again, R.id.findpass_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                if (findpassNext.getText().toString().equals("找回密码")){
                    step1.setVisibility(View.VISIBLE);
                    step2.setVisibility(View.GONE);
                    findpassNext.setText("下一步");
                }else {
                    FindpassActivity.this.finish();
                }
                break;
            case R.id.findpass_step1_getcode:
                break;
            case R.id.findpass_step2_pass_cansee_again:
                break;
            case R.id.findpass_next:
                step1.setVisibility(View.GONE);
                step2.setVisibility(View.VISIBLE);
                findpassNext.setText("找回密码");
                break;
        }
    }
}
