package com.nopossible.activities.main.shopping;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.confirmorder.ConfirmorderActivity;
import com.nopossible.adapter.CartItemAdapter;
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

public class ShoppingFragment extends MVPBaseFragment<ShoppingContract.View, ShoppingPresenter> implements ShoppingContract.View, CartItemAdapter.OnItemClickListener {


    @BindView(R.id.cart_manage)
    TextView cartManage;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.cart_recy)
    RecyclerView cartRecy;
    @BindView(R.id.cart_all)
    CheckBox cartAll;
    @BindView(R.id.cart_price)
    TextView cartPrice;
    @BindView(R.id.cart_account)
    TextView cartAccount;
    Unbinder unbinder;

    private CartItemAdapter mAdapter;
    private List<String> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        empty.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new CartItemAdapter(getContext(),mData);
        cartRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClick(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cart_manage, R.id.cart_all, R.id.cart_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cart_manage:
                break;
            case R.id.cart_all:
                break;
            case R.id.cart_account:
                Intent intent = new Intent(getContext(), ConfirmorderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    @Override
    public void onSubClick(View v, int position) {

    }

    @Override
    public void onPlusClick(View v, int position) {

    }

    @Override
    public void onCheckClick(CheckBox checkBox, int position) {

    }
}
