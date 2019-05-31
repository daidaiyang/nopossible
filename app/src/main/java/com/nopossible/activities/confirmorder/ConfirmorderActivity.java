package com.nopossible.activities.confirmorder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.myaddress.MyAddressActivity;
import com.nopossible.adapter.ConfirmOrderOrderFenAdapter;
import com.nopossible.customview.ShadowDrawable;
import com.nopossible.dialog.ConfirmPayDialog;
import com.nopossible.dialog.PeisongDialog;
import com.nopossible.entity.beans.MyAddressListBean;
import com.nopossible.entity.beans.SplitOrderResultBean;
import com.nopossible.entity.beans.SplitOrder_orderList;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.AppUtil;
import com.nopossible.utils.IntentUtil;
import com.nopossible.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.confirmorder_name)
    TextView confirmorderName;
    @BindView(R.id.confirmorder_tel)
    TextView confirmorderTel;
    @BindView(R.id.confirmorder_address)
    TextView confirmorderAddress;
    @BindView(R.id.confirmorder_address_rl)
    RelativeLayout addressRl;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.confirmorder_num)
    TextView confirmorderNum;
    @BindView(R.id.confirmorder_price)
    TextView confirmorderPrice;
    @BindView(R.id.confirmorder_commit)
    TextView confirmorderCommit;
    @BindView(R.id.chaidan_info_rl)
    RelativeLayout chaidanInfoRl;
    @BindView(R.id.confirmorder_address_rl_no)
    RelativeLayout addressRlNo;

    private PeisongDialog mPeisongDialog = null;
    private ConfirmPayDialog mDialog = null;
    private int REQUESTCODE = 0X0045641;

    private MyAddressListBean beans;


    private ConfirmOrderOrderFenAdapter mAdapter;
    private List<SplitOrder_orderList> mData;

    private SplitOrderResultBean orderBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mData = new ArrayList<>();
        titleTxt.setText("确认下单");
        titleRight.setVisibility(View.GONE);
        mPeisongDialog = new PeisongDialog(getContext());
        mDialog = new ConfirmPayDialog(getContext());
        mDialog.setOnDialogClickListener(onPayClick);
        ShadowDrawable.setShadowDrawable(addressRl, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x8),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x8),
                0, 0);
        ShadowDrawable.setShadowDrawable(addressRlNo, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x8),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x8),
                0, 0);
        mAdapter = new ConfirmOrderOrderFenAdapter(getContext(), mData);
        mAdapter.setOnItemClick(onClick);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setNestedScrollingEnabled(false);
        recycler.setAdapter(mAdapter);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void EventDataDeal(SplitOrderResultBean bean) {
        orderBean = bean;
        if (mData == null) {
            mData = new ArrayList<>();
        } else {
            mData.clear();
        }
        String address = bean.getOrder_list().get(0).getAddress();
        if (address == null || address.equals("")) {
            addressRlNo.setVisibility(View.VISIBLE);
            addressRl.setVisibility(View.GONE);
        } else {
            addressRl.setVisibility(View.VISIBLE);
            addressRlNo.setVisibility(View.GONE);
        }
        if (bean.getOrder_list().size() > 1) {
            chaidanInfoRl.setVisibility(View.VISIBLE);
        } else {
            chaidanInfoRl.setVisibility(View.GONE);
        }
        confirmorderPrice.setText("￥ " + AppUtil.get2xiaoshu(bean.getTotal_money()));
        mData.addAll(bean.getOrder_list());
        mAdapter.notifyDataSetChanged();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgDeal(MyAddressListBean beans){
        this.beans = beans;
        addressRlNo.setVisibility(View.GONE);
        addressRl.setVisibility(View.VISIBLE);
        confirmorderName.setText(beans.getContacts());
        confirmorderTel.setText(beans.getPhone());
        confirmorderAddress.setText(beans.getProvince_name()+beans.getCity_name()+beans.getDistrict_name()+beans.getAddress());
    }

    @OnClick({R.id.title_back, R.id.confirmorder_commit, R.id.confirmorder_address_rl_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                ConfirmorderActivity.this.finish();
                break;
            case R.id.confirmorder_commit:
                mPresenter.createOrder(beans,mData);
                break;
            case R.id.confirmorder_address_rl_no:
                IntentUtil.startActivity(ConfirmorderActivity.this, MyAddressActivity.class);
                break;
        }
    }

    @Override
    public void payOrder(SplitOrderResultBean money) {
                if (mDialog == null) {
                    mDialog = new ConfirmPayDialog(getContext());
                }
                mDialog.setMoney(money.getTotal_money());
                mDialog.show();
    }

    private ConfirmOrderOrderFenAdapter.OnItemClick onClick = new ConfirmOrderOrderFenAdapter.OnItemClick() {
        @Override
        public void onPeisongSjClick(View view, int position) {
            mPresenter.getMerchantList();
//            if (mPeisongDialog == null){
//                mPeisongDialog = new PeisongDialog(getContext());
//            }
//            mPeisongDialog.show();
        }

        @Override
        public void onSelectTimeClick(View view, int position) {
        }
    };

    private ConfirmPayDialog.OnDialogClickListener onPayClick = new ConfirmPayDialog.OnDialogClickListener() {
        @Override
        public void onPayClick(View view, int payType, String money) {
            List<SplitOrder_orderList> order_list = orderBean.getOrder_list();
            String order_no_list[] = new String[order_list.size()];
            for (int i=0;i<order_list.size();i++){
                order_no_list[i] = order_list.get(i).getNo();
            }
            if (payType == 0){//支付宝

            }else if (payType == 1){//微信
                mPresenter.payByWeChat(money,order_no_list);
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
