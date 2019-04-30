package com.nopossible.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossible.R;
import com.nopossible.entity.beans.OrderLineBean;
import com.nopossible.entity.beans.OrderLine_Product;
import com.nopossible.utils.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<OrderLineBean> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ConfirmOrderItemAdapter(Context mContext, List<OrderLineBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_confirmorder_item, viewGroup, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            if (i == mData.size()-1){
                holder.confirmorderItemLine.setVisibility(View.GONE);
            }else {
                holder.confirmorderItemLine.setVisibility(View.VISIBLE);
            }
        OrderLineBean orderLineBean = mData.get(i);
        OrderLine_Product product = orderLineBean.getProduct();
        Glide.with(mContext)
                .load(product.getImages_list().get(0).getUrl())
                .into(holder.confirmorderItemImg);
        holder.confirmorderItemTitle.setText(product.getName());
        holder.confirmorderItemPrice.setText("￥"+AppUtil.get2xiaoshu(product.getSell_price()));
        holder.confirmorderItemNum.setText("x"+orderLineBean.getNum());
    }

    @Override
    public int getItemCount() {
        return mData ==null?0:mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.confirmorder_item_img)
        ImageView confirmorderItemImg;
        @BindView(R.id.confirmorder_item_title)
        TextView confirmorderItemTitle;
        @BindView(R.id.confirmorder_item_price)
        TextView confirmorderItemPrice;
        @BindView(R.id.confirmorder_item_num)
        TextView confirmorderItemNum;
        @BindView(R.id.confirmorder_item_line)
        View confirmorderItemLine;

        private OnItemClickListener onItemClickListener;
        ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener!=null){
                onItemClickListener.onItemClick(v,getPosition());
            }
        }
    }

    interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
