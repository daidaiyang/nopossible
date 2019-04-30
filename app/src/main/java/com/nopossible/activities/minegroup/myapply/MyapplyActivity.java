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
import com.nopossible.entity.beans.MyApplyBean;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.IntentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyapplyActivity extends MVPBaseActivity<MyapplyContract.View, MyapplyPresenter> implements MyapplyContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


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
    @BindView(R.id.bga)
    BGARefreshLayout bga;


    private List<MyApplyBean> mData;
    private MineMyApplyItemAdapter mAdapter;
    private BGANormalRefreshViewHolder holder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_myapply);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("我的申购");
        myapplyNext.setTag(1);
        empty.setVisibility(View.GONE);
        titleRight.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mAdapter = new MineMyApplyItemAdapter(getContext(), mData);
        bga.setDelegate(this);
        holder = new BGANormalRefreshViewHolder(getContext(),true);
        holder.setPullDownRefreshText("下拉刷新");
        holder.setRefreshingText("刷新中...");
        holder.setLoadingMoreText("加载中...");
        bga.setRefreshViewHolder(holder);
        myapplyRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        myapplyRecy.setAdapter(mAdapter);
        mPresenter.getList();
    }


    @Override
    public void setNoData() {
        empty.setVisibility(View.VISIBLE);
        myapplyRecy.setVisibility(View.GONE);
        myapplyNext.setText("我要申购");
        myapplyNext.setTag(1);
        myapplyNum.setText("共0件");
    }

    @Override
    public void setData(List<MyApplyBean> data) {
        empty.setVisibility(View.GONE);
        myapplyRecy.setVisibility(View.VISIBLE);
        myapplyNext.setText("继续申购");
        myapplyNum.setText(String.format("共%d件",data.size()));
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.title_back, R.id.mine_myapply_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.mine_myapply_next:
                IntentUtil.startActivity(MyapplyActivity.this, com.nopossible.activities.myapply.MyapplyActivity.class);
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
