package com.nopossible.activities.confirmreceipt;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.ConfirmreceiptLeftAdapter;
import com.nopossible.adapter.ConfirmreceiptRightAdapter;
import com.nopossible.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ConfirmreceiptActivity extends MVPBaseActivity<ConfirmreceiptContract.View, ConfirmreceiptPresenter> implements ConfirmreceiptContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.confirmrecepit_left_recy)
    RecyclerView confirmrecepitLeftRecy;
    @BindView(R.id.confirmrecepit_right_recy)
    RecyclerView confirmrecepitRightRecy;
    @BindView(R.id.confirmrecepit_num)
    TextView confirmrecepitNum;
    @BindView(R.id.confirmrecepit_price)
    TextView confirmrecepitPrice;
    @BindView(R.id.confirmrecepit_get)
    TextView confirmrecepitGet;


    private List<String> mLeftData;
    private List<String> mRightData;
    private ConfirmreceiptRightAdapter mRightAdapter;
    private ConfirmreceiptLeftAdapter mLeftAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmreceipt);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("待收货");
        titleRight.setVisibility(View.GONE);
        mLeftData = new ArrayList<>();
        mLeftData.add("");
        mLeftData.add("");
        mLeftData.add("");
        mRightData = new ArrayList<>();
        mRightData.add("");
        mRightData.add(""); mRightData.add(""); mRightData.add(""); mRightData.add("");
        mLeftAdapter = new ConfirmreceiptLeftAdapter(getContext(),mLeftData);
        mRightAdapter = new ConfirmreceiptRightAdapter(getContext(),mRightData);
        confirmrecepitLeftRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        confirmrecepitRightRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        confirmrecepitLeftRecy.setAdapter(mLeftAdapter);
        confirmrecepitRightRecy.setAdapter(mRightAdapter);

    }

    @OnClick({R.id.title_back, R.id.confirmrecepit_get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.confirmrecepit_get:
                break;
        }
    }
}
