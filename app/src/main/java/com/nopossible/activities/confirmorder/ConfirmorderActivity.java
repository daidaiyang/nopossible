package com.nopossible.activities.confirmorder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.ConfirmOrderItemAdapter;
import com.nopossible.customview.ShadowDrawable;
import com.nopossible.dialog.ConfirmPayDialog;
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

public class ConfirmorderActivity extends MVPBaseActivity<ConfirmorderContract.View, ConfirmorderPresenter> implements ConfirmorderContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.confirmorder_address_rl)
    RelativeLayout addressRl;
    @BindView(R.id.confirmorder_recy)
    RecyclerView recy;
    @BindView(R.id.confirmorder_info_ll)
    LinearLayout infoLl;
    @BindView(R.id.confirmorder_name)
    TextView confirmorderName;
    @BindView(R.id.confirmorder_tel)
    TextView confirmorderTel;
    @BindView(R.id.confirmorder_address)
    TextView confirmorderAddress;
    @BindView(R.id.confirmorder_time)
    TextView confirmorderTime;
    @BindView(R.id.confirmorder_business)
    TextView confirmorderBusiness;
    @BindView(R.id.confirmorder_mode)
    TextView confirmorderMode;
    @BindView(R.id.confirmorder_message)
    EditText confirmorderMessage;
    @BindView(R.id.confirmorder_num)
    TextView confirmorderNum;
    @BindView(R.id.confirmorder_price)
    TextView confirmorderPrice;
    @BindView(R.id.confirmorder_commit)
    TextView confirmorderCommit;

    private ConfirmPayDialog mDialog = null;


    private ConfirmOrderItemAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("确认下单");
        titleRight.setVisibility(View.GONE);
        ShadowDrawable.setShadowDrawable(addressRl, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x12),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x15),
                0, 0);
        ShadowDrawable.setShadowDrawable(infoLl, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x12),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x15),
                0, 0);
        ShadowDrawable.setShadowDrawable(recy, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x12),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x15),
                0, 0);
        initData();
        mAdapter = new ConfirmOrderItemAdapter(getContext(), mData);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
    }

    @OnClick({R.id.title_back, R.id.confirmorder_info_ll,R.id.confirmorder_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                ConfirmorderActivity.this.finish();
                break;
            case R.id.confirmorder_info_ll:
                break;
            case R.id.confirmorder_commit:
                if (mDialog == null){
                    mDialog = new ConfirmPayDialog(getContext());
                }
                mDialog.show();
                break;
        }
    }
}
