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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.confirmorder.ConfirmorderActivity;
import com.nopossible.activities.gooddetail.GooddetailActivity;
import com.nopossible.adapter.CartItemAdapter;
import com.nopossible.entity.beans.ShopCarProductBean;
import com.nopossible.mvp.MVPBaseFragment;
import com.nopossible.utils.AppUtil;
import com.nopossible.utils.IntentUtil;
import com.nopossible.utils.ToastUtil;

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

public class ShoppingFragment extends MVPBaseFragment<ShoppingContract.View, ShoppingPresenter> implements ShoppingContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


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
    @BindView(R.id.bga)
    BGARefreshLayout bga;
    @BindView(R.id.bottom_message)
    LinearLayout bottomMessage;
    @BindView(R.id.cart_delete)
    TextView cartDelete;
    @BindView(R.id.bottom)
    RelativeLayout bottom;

    private CartItemAdapter mAdapter;
    private List<ShopCarProductBean> mData;

    private boolean isInitView = false;
    private boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        isInitView = true;
        return view;
    }

    private void initView() {
        cartManage.setTag(0);
        bga.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格
        bga.setRefreshViewHolder(refreshViewHolder);
        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
        bga.setIsShowLoadingMoreView(false);
        // 设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("加载中...");
        empty.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mAdapter = new CartItemAdapter(getContext(), mData);
        cartRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClick(itemClick);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }
    }

    private void isCanLoadData() {
        if (isInitView && isVisible) {
            mPresenter.getShopCarData();
            isInitView = false;
            isVisible = false;
        }
    }


    /**
     * 首次加载数据
     *
     * @param data
     */
    public void initData(List<ShopCarProductBean> data) {
        mData.clear();
        mData.addAll(data);
        if (mData.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            bottom.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
        countPrice();
    }

    /**
     * 刷新数据
     *
     * @param data
     */
    public void refreshData(List<ShopCarProductBean> data) {
        mData.clear();
        mData.addAll(data);
        if (mData.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            bottom.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
        bga.endRefreshing();
        countPrice();
    }

    /**
     * 加载数据
     *
     * @param data
     */
    public void loadData(List<ShopCarProductBean> data) {
        mData.addAll(mData.size(), data);
        mAdapter.notifyDataSetChanged();
        bga.endLoadingMore();
        countPrice();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cart_manage, R.id.cart_all, R.id.cart_account, R.id.cart_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cart_manage:
                if ((int) cartManage.getTag() == 1) {
                    //完成
                    cartManage.setText("管理");
                    cartManage.setTag(0);

                    bottomMessage.setVisibility(View.VISIBLE);
                    cartAccount.setVisibility(View.VISIBLE);
                    cartDelete.setVisibility(View.GONE);
                } else {
                    //管理
                    cartManage.setText("完成");
                    cartManage.setTag(1);
                    bottomMessage.setVisibility(View.GONE);
                    cartAccount.setVisibility(View.GONE);
                    cartDelete.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.cart_all:
                checkAllProduct();
                countPrice();
                break;
            case R.id.cart_account:
                countProduct();
                break;
            case R.id.cart_delete:
                deleteProduct();
                break;
        }
    }

    //结算
    private void countProduct(){
        List<String> ids = new ArrayList<>();
        for (ShopCarProductBean bean:mData
                ) {
            if (bean.isChecked()){
                ids.add(bean.getProduct_id());
            }
        }
        if (ids.size()==0){
            ToastUtil.showBottomToast("请先选择要结算的商品");
        }else {
            mPresenter.countShopping(ids);
        }
    }

    private void deleteProduct() {
        List<String> product_ids = new ArrayList<>();
        for (ShopCarProductBean bean : mData
                ) {
            if (bean.isChecked()) {
                product_ids.add(bean.getProduct_id());
            }
        }
        mPresenter.deleteProduct(product_ids);
    }


    private void checkAllProduct() {
        List<ShopCarProductBean> data = new ArrayList<>();
        for (ShopCarProductBean bean : mData) {
            bean.setChecked(cartAll.isChecked());
            data.add(bean);
        }
        this.mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    private void changeData() {
        List<ShopCarProductBean> data = new ArrayList<>();
        for (ShopCarProductBean bean : mData
                ) {
            data.add(bean);
        }
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        countPrice();
    }

    private CartItemAdapter.OnItemClickListener itemClick = new CartItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("product", mData.get(position));
            IntentUtil.startActivity(getContext(), GooddetailActivity.class, bundle);

        }

        @Override
        public void onSubClick(View v, int position) {
            ShopCarProductBean shopCarProductBean = mData.get(position);
            int num = shopCarProductBean.getNum();
            num--;
            mPresenter.changeNum(shopCarProductBean.getProduct_id(), num);
        }

        @Override
        public void onPlusClick(View v, int position) {
            ShopCarProductBean shopCarProductBean = mData.get(position);
            int num = shopCarProductBean.getNum();
            num++;
            mPresenter.changeNum(shopCarProductBean.getProduct_id(), num);
        }

        @Override
        public void onCheckClick(CheckBox checkBox, int position) {
            mData.get(position).setChecked(checkBox.isChecked());
            changeData();
        }
    };

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.freshData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.loadData();
        return false;
    }

    private void countPrice() {
        double allPrice = 0;
        for (ShopCarProductBean bean : mData
                ) {
            if (bean.isChecked()) {
                double money = Double.valueOf(bean.getMoney());
                allPrice += money;
            }
        }
        cartPrice.setText(AppUtil.get2xiaoshu(allPrice));
    }
}
