package com.nopossible.activities.changepassword;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.AppUtil;
import com.nopossible.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ChangepasswordActivity extends MVPBaseActivity<ChangepasswordContract.View, ChangepasswordPresenter> implements ChangepasswordContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.oldpassword)
    EditText oldpassword;
    @BindView(R.id.newpassword)
    EditText newpassword;
    @BindView(R.id.newpassword_again)
    EditText newpasswordAgain;
    @BindView(R.id.confirm)
    TextView confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleRight.setVisibility(View.GONE);
        titleTxt.setText("修改密码");
    }

    @OnClick({R.id.title_back, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.confirm:
                getPassword();
                break;
        }
    }

    private void getPassword() {
        String s = oldpassword.getText().toString();
        String new1 = newpassword.getText().toString();
        String new2 = newpasswordAgain.getText().toString();
        if (s.equals("")||"".equals(new1)||new2.equals("")){
            ToastUtil.showBottomToast("密码不能为空");
        }else
        if (!AppUtil.isLetterDigit(new1)||new1.length()<6){
            ToastUtil.showBottomToast("密码需6位数字以上，包含字母和数字");
        }else if (!new1.equals(new2)){
            ToastUtil.showBottomToast("请确认两次输入的密码一致");
        }else {
            mPresenter.changePass(s,new1);
        }

    }
}
