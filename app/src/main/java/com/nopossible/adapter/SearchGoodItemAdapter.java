package com.nopossible.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossible.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchGoodItemAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<String> mData;

    public SearchGoodItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_searchgood_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
