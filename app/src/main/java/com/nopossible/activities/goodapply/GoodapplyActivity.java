package com.nopossible.activities.goodapply;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.ShengouItemAdapter;
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

public class GoodapplyActivity extends MVPBaseActivity<GoodapplyContract.View, GoodapplyPresenter> implements GoodapplyContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.goodapply_num)
    TextView goodapplyNum;
    @BindView(R.id.goodapply_recy)
    RecyclerView goodapplyRecy;
    @BindView(R.id.goodapply_apply)
    TextView goodapplyApply;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.goodapply_empty)
    LinearLayout goodapplyEmpty;
    @BindView(R.id.goodapply_content)
    LinearLayout goodapplyContent;

    private List<String> mData;
    private ShengouItemAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodapply);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("商品申购");
        emptyInfo.setText("暂无申购中的商品");
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new ShengouItemAdapter(getContext(), mData);
        goodapplyRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        goodapplyRecy.setAdapter(mAdapter);
    }

    @OnClick({R.id.title_back, R.id.goodapply_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.goodapply_apply:
                break;
        }
    }
}
