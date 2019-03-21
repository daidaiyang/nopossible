package com.nopossible.activities.minegroup.mymessage;


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

public class MymessageActivity extends MVPBaseActivity<MymessageContract.View, MymessagePresenter> implements MymessageContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymessage);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("消息");
        titleRight.setVisibility(View.GONE);
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        this.finish();
    }
}
