package com.nopossible.activities.main.order;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.confirmreceipt.ConfirmreceiptActivity;
import com.nopossible.adapter.MineOrderManagerAdapter;
import com.nopossible.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderFragment extends MVPBaseFragment<OrderContract.View, OrderPresenter> implements OrderContract.View, MineOrderManagerAdapter.OnItemClickListener {

    @BindView(R.id.ordermanage_search)
    ImageView ordermanageSearch;
    @BindView(R.id.ordermanage_all)
    RadioButton ordermanageAll;
    @BindView(R.id.ordermanage_unpay)
    RadioButton ordermanageUnpay;
    @BindView(R.id.ordermanage_unget)
    RadioButton ordermanageUnget;
    @BindView(R.id.ordermanage_unsay)
    RadioButton ordermanageUnsay;
    @BindView(R.id.ordermanage_finish)
    RadioButton ordermanageFinish;
    @BindView(R.id.ordermanage_rg)
    RadioGroup ordermanageRg;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.ordermanage_recy)
    RecyclerView ordermanageRecy;
    Unbinder unbinder;
    //选项卡下面的横线
    private Drawable drawable = null;

    private MineOrderManagerAdapter mAdapter;
    private List<String> mData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        ordermanageAll.setChecked(true);
        clearAllDrawable();
        setDrawable(0);
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new MineOrderManagerAdapter(getContext(),mData);
        ordermanageRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ordermanageRecy.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ordermanage_search, R.id.ordermanage_all, R.id.ordermanage_unpay, R.id.ordermanage_unget, R.id.ordermanage_unsay, R.id.ordermanage_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ordermanage_search:
                break;
            case R.id.ordermanage_all:
                    clearAllDrawable();
                    setDrawable(0);
                break;
            case R.id.ordermanage_unpay:
                    clearAllDrawable();
                    setDrawable(1);
                break;
            case R.id.ordermanage_unget:
                clearAllDrawable();
                setDrawable(2);
                break;
            case R.id.ordermanage_unsay:
                clearAllDrawable();
                setDrawable(3);
                break;
            case R.id.ordermanage_finish:
                clearAllDrawable();
                setDrawable(4);
                break;
        }
    }

    private void clearAllDrawable(){
        ordermanageAll.setCompoundDrawables(null,null,null,null);
        ordermanageUnpay.setCompoundDrawables(null,null,null,null);
        ordermanageUnget.setCompoundDrawables(null,null,null,null);
        ordermanageUnsay.setCompoundDrawables(null,null,null,null);
        ordermanageFinish.setCompoundDrawables(null,null,null,null);
    }

    private void setDrawable(int position){
        if (drawable == null){
            drawable = getResources().getDrawable(R.drawable.rect_circle_x4_0f);
            drawable.setBounds(0,0,(int)getResources().getDimension(R.dimen.x38),(int) getResources().getDimension(R.dimen.x8));
        }
        switch (position){
            case 0:
                ordermanageAll.setCompoundDrawables(null,null,null,drawable);
                break;
            case 1:
                ordermanageUnpay.setCompoundDrawables(null,null,null,drawable);
                break;
            case 2:
                ordermanageUnget.setCompoundDrawables(null,null,null,drawable);
                break;
            case 3:
                ordermanageUnsay.setCompoundDrawables(null,null,null,drawable);
                break;
            case 4:
                ordermanageFinish.setCompoundDrawables(null,null,null,drawable);
                break;
        }
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    @Override
    public void onAgainClick(View v, int position) {

    }

    @Override
    public void onOperationClick(View v, int position) {
        Intent intent = new Intent(getContext(), ConfirmreceiptActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
