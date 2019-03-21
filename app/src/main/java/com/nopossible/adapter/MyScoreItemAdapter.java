package com.nopossible.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nopossible.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyScoreItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;

    public MyScoreItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_myscore_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_score_type)
        TextView itemScoreType;
        @BindView(R.id.item_score_time)
        TextView itemScoreTime;
        @BindView(R.id.item_score)
        TextView itemScore;
        @BindView(R.id.item_leftScore)
        TextView itemLeftScore;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
