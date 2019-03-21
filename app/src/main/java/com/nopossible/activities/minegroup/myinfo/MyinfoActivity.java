package com.nopossible.activities.minegroup.myinfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
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

public class MyinfoActivity extends MVPBaseActivity<MyinfoContract.View, MyinfoPresenter> implements MyinfoContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("个人资料");
        titleRight.setVisibility(View.GONE);
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        MyinfoActivity.this.finish();
    }
}
