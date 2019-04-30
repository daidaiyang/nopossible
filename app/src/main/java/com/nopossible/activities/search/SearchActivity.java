package com.nopossible.activities.search;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.gooddetail.GooddetailActivity;
import com.nopossible.adapter.SearchGoodItemAdapter;
import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.IntentUtil;
import com.nopossible.utils.RecycleViewDivider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchActivity extends MVPBaseActivity<SearchContract.View, SearchPresenter> implements SearchContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.first_result_back)
    ImageView firstResultBack;
    @BindView(R.id.first_result_title)
    TextView firstResultTitle;
    @BindView(R.id.first_result_word)
    TextView firstResultWord;
    @BindView(R.id.first_result_word_long)
    TextView firstResultWordLong;
    @BindView(R.id.first_result_icon)
    ImageView firstResultIcon;
    @BindView(R.id.first_result)
    RelativeLayout firstResult;
    @BindView(R.id.first_bga_recycle)
    RecyclerView firstBgaRecycle;
    @BindView(R.id.first_bga)
    BGARefreshLayout firstBga;
    @BindView(R.id.first_result_view)
    LinearLayout firstResultView;

    private SearchGoodItemAdapter mAdapter;
    private List<ProductListBean> mData;
    private String key = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        key = getIntent().getExtras().getString("key");
        firstResultWord.setText(String.format("\"%s\"",key));
        firstResultWordLong.setText(String.format("您要找的\"%s\"已为您找到",key));
        firstBga.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        firstBga.setRefreshViewHolder(refreshViewHolder);
        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
        firstBga.setIsShowLoadingMoreView(false);
        // 设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("加载中...");
        firstBgaRecycle.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        firstBgaRecycle.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL, (int) getContext().getResources().getDimension(R.dimen.x10),
                getContext().getResources().getColor(R.color.first_back)));
        mData = new ArrayList<>();
        mAdapter = new SearchGoodItemAdapter(getContext(), mData);
        mAdapter.setmListener(adapterListener);
        firstBgaRecycle.setAdapter(mAdapter);
        initData();
    }


    /**
     * 首次刷新数据
     */
    private void initData() {
        if (!key.equals("")) {
            firstBga.beginRefreshing();
            mPresenter.search("测试商品");
        }
    }


    /**
     * 首次加载数据
     */
    public void upDataData(List<ProductListBean> mData){
        this.mData.clear();
        this.mData.addAll(mData);
        mAdapter.notifyDataSetChanged();
        firstBga.endLoadingMore();
    }
    /**
     * 刷新数据
     * @param mData
     */
    public void refreshData(List<ProductListBean> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        mAdapter.notifyDataSetChanged();
        firstBga.endRefreshing();
    }

    /**
     * 加载数据
     * @param mData
     */
    public void loadData(List<ProductListBean> mData) {
        this.mData.addAll(this.mData.size(),mData);
        mAdapter.notifyDataSetChanged();
        firstBga.endRefreshing();
    }


    SearchGoodItemAdapter.OnItemClickListener adapterListener = new SearchGoodItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("product",mData.get(position));
            IntentUtil.startActivity(getContext(), GooddetailActivity.class,bundle);
        }

        @Override
        public void onAddCartClick(View v, int position) {
            String id = mData.get(position).getId();
            mPresenter.addtocart(id);
        }
    };

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.refresh(key);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.load(key);
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.first_result_back, R.id.first_result_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_result_back:
                this.finish();
                break;
            case R.id.first_result_icon:
                this.finish();
                break;
        }
    }
}
