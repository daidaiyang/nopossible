package com.nopossible.activities.main.first;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.gooddetail.GooddetailActivity;
import com.nopossible.adapter.SearchGoodItemAdapter;
import com.nopossible.customview.CircleImageView;
import com.nopossible.customview.WaveView;
import com.nopossible.dialog.RecognitionDialog;
import com.nopossible.mvp.MVPBaseFragment;
import com.nopossible.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FirstFragment extends MVPBaseFragment<FirstContract.View, FirstPresenter> implements FirstContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, SearchGoodItemAdapter.OnItemClickListener {


    @BindView(R.id.first_location_icon)
    ImageView firstLocationIcon;
    @BindView(R.id.first_location_txt)
    TextView firstLocationTxt;
    @BindView(R.id.first_record_txt1)
    TextView firstRecordTxt1;
    @BindView(R.id.first_record_txt2)
    TextView firstRecordTxt2;
    @BindView(R.id.first_record_low_icon)
    ImageView firstRecordLowIcon;
    @BindView(R.id.first_record_icon)
    WaveView firstRecordIcon;
    @BindView(R.id.first_function_scan_icon)
    ImageView firstFunctionScanIcon;
    @BindView(R.id.first_function_scan_txt)
    TextView firstFunctionScanTxt;
    @BindView(R.id.first_function_scan)
    LinearLayout firstFunctionScan;
    @BindView(R.id.first_function_order_icon)
    ImageView firstFunctionOrderIcon;
    @BindView(R.id.first_function_order_txt)
    TextView firstFunctionOrderTxt;
    @BindView(R.id.first_function_order)
    LinearLayout firstFunctionOrder;
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
    Unbinder unbinder;
    @BindView(R.id.first_record_icon_img)
    ImageView firstRecordIconImg;
    @BindView(R.id.first_record_circle)
    CircleImageView firstRecordCircle;
    @BindView(R.id.first_search_view)
    LinearLayout firstSearchView;
    @BindView(R.id.first_result_view)
    LinearLayout firstResultView;

    private SearchGoodItemAdapter mAdapter;
    private List<String> mData;
    private Animation animation = null;
    private Animator animationJump = null;

    private long downTime;
    private RecognitionDialog dialog = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.image_scale);
        animationJump = AnimatorInflater.loadAnimator(getContext(), R.animator.heart_jump);
        animationJump.setTarget(firstRecordCircle);
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
        // 设置整个加载更多控件的背景颜色资源 id
//        refreshViewHolder.setLoadMoreBackgroundColorRes(loadMoreBackgroundColorRes);
        // 设置整个加载更多控件的背景 drawable 资源 id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
        // 设置下拉刷新控件的背景颜色资源 id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
        // 设置下拉刷新控件的背景 drawable 资源 id
//        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
//        firstBga.setCustomHeaderView(mBanner, false);
        // 可选配置  -------------END
        firstBgaRecycle.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        firstBgaRecycle.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL, (int) getContext().getResources().getDimension(R.dimen.x10),
                getContext().getResources().getColor(R.color.first_back)));
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new SearchGoodItemAdapter(getContext(), mData);
        mAdapter.setmListener(this);
        firstBgaRecycle.setAdapter(mAdapter);
        firstRecordIcon.setDuration(2000);
        firstRecordIcon.setInitialRadius(getContext().getResources().getDimension(R.dimen.x280) / 2.0f);
        firstRecordIcon.setMaxRadius(getContext().getResources().getDimension(R.dimen.x360) / 2.0f);
        firstRecordIcon.setStyle(Paint.Style.FILL);
        firstRecordIcon.setColor(Color.parseColor("#FEEE94"));
        firstRecordIcon.setInterpolator(new LinearOutSlowInInterpolator());
        firstRecordIcon.start();
        firstRecordIconImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downTime = System.currentTimeMillis();
                        animation.setFillAfter(true);
                        firstRecordIconImg.startAnimation(animation);
                        firstRecordIcon.stopImmediately();
                        firstRecordCircle.setVisibility(View.VISIBLE);
                        animationJump.start();
                        break;
                    case MotionEvent.ACTION_UP:
                        firstRecordCircle.setVisibility(View.GONE);
                        animationJump.cancel();
                        firstRecordIconImg.clearAnimation();
                        firstRecordIcon.start();
                        if (System.currentTimeMillis() - downTime > 500) {
                            showDialog();
                            new Handler()
                                    .postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            firstSearchView.setVisibility(View.GONE);
                                            firstResultView.setVisibility(View.VISIBLE);
                                            dialog.cancel();
                                        }
                                    }, 3000);
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void showDialog() {
        if (dialog == null) {
            dialog = new RecognitionDialog(getContext());
        }
        dialog.show();
        dialog.setCloseListener(new RecognitionDialog.CloseListener() {
            @Override
            public void close() {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.first_function_scan, R.id.first_function_order, R.id.first_result_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_function_scan:
                break;
            case R.id.first_function_order:
                break;
            case R.id.first_result_icon:
                break;
        }
    }

    // 通过代码方式控制进入正在刷新状态。应用场景：某些应用在 activity 的 onStart 方法中调用，自动进入正在刷新状态获取最新数据
    public void beginRefreshing() {
        firstBga.beginRefreshing();
    }

    // 通过代码方式控制进入加载更多状态
    public void beginLoadingMore() {
        firstBga.beginLoadingMore();
    }

    //刷新
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firstBga.endRefreshing();
            }
        }, 3000);

    }

    //加载
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firstBga.endLoadingMore();
            }
        }, 3000);

        return true;
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(getContext(),GooddetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onAddCartClick(View v, int position) {
        Log.d("ssssssssssss","sssssssssssssssssssssss"+position);
    }
}
