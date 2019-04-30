package com.nopossible.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.customview.ShadowDrawable;
import com.nopossible.entity.beans.OrderLineBean;
import com.nopossible.entity.beans.OrderLine_Product;
import com.nopossible.entity.beans.SplitOrder_orderList;
import com.nopossible.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderOrderFenAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<SplitOrder_orderList> mData;


    public ConfirmOrderOrderFenAdapter(Context mContext, List<SplitOrder_orderList> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_confirmorder_order_chaifen, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        SplitOrder_orderList splitOrder_orderList = mData.get(i);
        if (mData.size() == 1) {
            holder.orderInfo.setVisibility(View.GONE);
        } else {
            holder.orderInfo.setVisibility(View.VISIBLE);
            holder.orderNum.setText("订单"+i);
            holder.orderProductnum.setText(String.format("共%d件",mData.get(i).getOrder_line().size()));
        }

        if (Float.valueOf(splitOrder_orderList.getPay_money())<100.0f){
            holder.orderSendfeeImg.setVisibility(View.VISIBLE);
            holder.orderSendfeeInfo.setVisibility(View.VISIBLE);
            holder.orderSendfee.setText("￥"+AppUtil.get2xiaoshu(splitOrder_orderList.getDelivery_money()));
        }else {
            holder.orderSendfeeImg.setVisibility(View.GONE);
            holder.orderSendfeeInfo.setVisibility(View.GONE);
            holder.orderSendfee.setText("￥0.00");
        }
        holder.orderXiaoji.setText("￥"+AppUtil.get2xiaoshu(splitOrder_orderList.getPay_money()));
        ShadowDrawable.setShadowDrawable(holder.root, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x8),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x10),
                0, 0);
        List<OrderLineBean> mData = splitOrder_orderList.getOrder_line();
        ConfirmOrderItemAdapter mAdapter = new ConfirmOrderItemAdapter(mContext, mData);
        holder.recy.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recy.setNestedScrollingEnabled(false);
        holder.recy.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_sendfee_img)
        ImageView orderSendfeeImg;
        @BindView(R.id.order_sendfee_info)
        TextView orderSendfeeInfo;
        @BindView(R.id.order_info)
        LinearLayout orderInfo;
        @BindView(R.id.root)
        LinearLayout root;
        @BindView(R.id.order_num)
        TextView orderNum;
        @BindView(R.id.order_name)
        TextView orderName;
        @BindView(R.id.order_productnum)
        TextView orderProductnum;
        @BindView(R.id.recy)
        RecyclerView recy;
        @BindView(R.id.select_time)
        TextView selectTime;
        @BindView(R.id.select_time_rl)
        RelativeLayout selectTimeRl;
        @BindView(R.id.select_dealer)
        TextView selectDealer;
        @BindView(R.id.select_dealer_rl)
        RelativeLayout selectDealerRl;
        @BindView(R.id.confirmorder_mode)
        TextView confirmorderMode;
        @BindView(R.id.message)
        EditText message;
        @BindView(R.id.info_ll)
        LinearLayout infoLl;
        @BindView(R.id.order_sendfee)
        TextView orderSendfee;
        @BindView(R.id.order_xiaoji)
        TextView orderXiaoji;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
