package com.nopossible.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossible.R;
import com.nopossible.customview.ShadowDrawable;
import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.entity.beans.ShopCarProductBean;
import com.nopossible.utils.AppUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ShopCarProductBean> mData;
    private OnItemClickListener onItemClick;

    public void setOnItemClick(OnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public CartItemAdapter(Context mContext, List<ShopCarProductBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_cart_item, viewGroup, false);
        return new ViewHolder(view,onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShopCarProductBean bean = mData.get(i);
        ProductListBean product = bean.getProduct();
        ShadowDrawable.setShadowDrawable(holder.root, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x20),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x15),
                0, 0);
        String url ="";
        try{
             url = product.getImages_list().get(0).getUrl();
        }catch (IndexOutOfBoundsException e){

        }
        Glide.with(mContext)
                .load(url)
                .into(holder.cartItemImg);
        holder.cartItemTitle.setText(product.getName());
        holder.cartItemPrice.setText("￥"+AppUtil.get2xiaoshu(product.getSell_price()));
        double allPrice = bean.getNum() * Double.parseDouble(product.getSell_price());
        holder.cartItemAllprice.setText(String.format("￥%s",AppUtil.get2xiaoshu(allPrice)));
        holder.cartItemNum.setText(String.valueOf(bean.getNum()));
        if (bean.isChecked()){
            holder.cartItemCheck.setChecked(true);
        }else {
            holder.cartItemCheck.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.cart_item_check)
        CheckBox cartItemCheck;
        @BindView(R.id.cart_item_img)
        ImageView cartItemImg;
        @BindView(R.id.cart_item_title)
        TextView cartItemTitle;
        @BindView(R.id.cart_item_price)
        TextView cartItemPrice;
        @BindView(R.id.cart_item_allprice)
        TextView cartItemAllprice;
        @BindView(R.id.cart_item_sub)
        ImageView cartItemSub;
        @BindView(R.id.cart_item_num)
        TextView cartItemNum;
        @BindView(R.id.cart_item_plus)
        ImageView cartItemPlus;
        @BindView(R.id.root)
        RelativeLayout root;
        private OnItemClickListener onItemClick;

        ViewHolder(View view, OnItemClickListener onItemClick) {
            super(view);
            ButterKnife.bind(this, view);
            this.onItemClick = onItemClick;
            view.setOnClickListener(this);
            cartItemPlus.setOnClickListener(this);
            cartItemSub.setOnClickListener(this);
            cartItemCheck.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClick != null) {
                switch (v.getId()) {
                    case R.id.cart_item_plus:
                        onItemClick.onPlusClick(v,getPosition());
                        break;
                    case R.id.cart_item_sub:
                        onItemClick.onSubClick(v,getPosition());
                        break;
                    case R.id.cart_item_check:
                        onItemClick.onCheckClick((CheckBox) v,getPosition());
                        break;
                    default:
                        onItemClick.onItemClick(v,getPosition());
                        break;
                }
            }
        }

    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onSubClick(View v, int position);

        void onPlusClick(View v, int position);

        void onCheckClick(CheckBox checkBox, int position);
    }
}
