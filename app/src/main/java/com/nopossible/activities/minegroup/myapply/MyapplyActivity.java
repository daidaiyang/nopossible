package com.nopossible.activities.minegroup.myapply;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.MineMyApplyItemAdapter;
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

public class MyapplyActivity extends MVPBaseActivity<MyapplyContract.View, MyapplyPresenter> implements MyapplyContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.mine_myapply_num)
    TextView myapplyNum;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.mine_myapply_recy)
    RecyclerView myapplyRecy;
    @BindView(R.id.mine_myapply_next)
    TextView myapplyNext;


    private List<String> mData;
    private MineMyApplyItemAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_myapply);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("我的申购");
        titleRight.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mAdapter = new MineMyApplyItemAdapter(getContext(),mData);
        myapplyRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        myapplyRecy.setAdapter(mAdapter);
    }

    @OnClick({R.id.title_back, R.id.mine_myapply_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.mine_myapply_next:
                break;
        }
    }
}
