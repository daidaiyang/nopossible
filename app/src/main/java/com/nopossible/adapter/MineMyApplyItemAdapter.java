package com.nopossible.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.customview.ShadowDrawable;
import com.nopossible.entity.beans.MyApplyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineMyApplyItemAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<MyApplyBean> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MineMyApplyItemAdapter(Context mContext, List<MyApplyBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_mine_myapply_item, viewGroup, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.itemView, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x20),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x15),
                0, 0);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.myapply_img)
        ImageView myapplyImg;
        @BindView(R.id.myapply_title)
        TextView myapplyTitle;
        @BindView(R.id.shen_info)
        TextView shenInfo;
        @BindView(R.id.shen_status)
        TextView shenStatus;
        @BindView(R.id.myapply_search)
        LinearLayout myapplySearch;

        private OnItemClickListener onItemClickListener;

        ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.onItemClickListener = onItemClickListener;
            view.setOnClickListener(this);
            myapplySearch.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myapply_search:
                    onItemClickListener.onSerachClick(v, getPosition());
                    break;
                default:
                    onItemClickListener.onItemClick(v, getPosition());
                    break;
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onSerachClick(View v, int position);
    }
}
