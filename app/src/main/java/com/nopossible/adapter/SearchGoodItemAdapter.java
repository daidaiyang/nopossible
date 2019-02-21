package com.nopossible.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.customview.ShadowDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchGoodItemAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<String> mData;
    private OnItemClickListener mListener;

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public SearchGoodItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_searchgood_item, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.root, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x20),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x15),
                0, 0);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.searchgood_item_goodimg)
        ImageView searchgoodItemGoodimg;
        @BindView(R.id.searchgood_item_title)
        TextView searchgoodItemTitle;
        @BindView(R.id.searchgood_item_price)
        TextView searchgoodItemPrice;
        @BindView(R.id.searchgood_item_standar)
        TextView searchgoodItemStandar;
        @BindView(R.id.searchgood_item_addcart)
        ImageView searchgoodItemAddcart;
        @BindView(R.id.root)
        RelativeLayout root;

        private OnItemClickListener mListener;

        ViewHolder(View view, OnItemClickListener mListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.mListener = mListener;
            view.setOnClickListener(this);
            searchgoodItemAddcart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.searchgood_item_addcart) {
                if (mListener != null)
                    mListener.onAddCartClick(v, getPosition());
            } else {
                if (mListener != null)
                    mListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onAddCartClick(View v, int position);
    }
}
