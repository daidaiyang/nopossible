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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.customview.ShadowDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShengouItemAdapter extends RecyclerView.Adapter {


    private List<String> mData;
    private Context mContext;
    private OnItemClickListener mClickListener;

    public void setmClickListener(OnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public ShengouItemAdapter(Context mContext, List<String> mData) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_shengou_item, viewGroup, false);
        return new ViewHolder(view,mClickListener);
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
        @BindView(R.id.shengou_item_goodimg)
        ImageView shengouItemGoodimg;
        @BindView(R.id.shengou_item_title)
        TextView shengouItemTitle;
        @BindView(R.id.shengou_item_have)
        TextView shengouItemHave;
        @BindView(R.id.shengou_item_status)
        TextView shengouItemStatus;
        @BindView(R.id.shengou_root)
        RelativeLayout shengouRoot;
        @BindView(R.id.root)
        LinearLayout root;
        private OnItemClickListener mClickListener;

        ViewHolder(View view,OnItemClickListener mClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            shengouRoot.setOnClickListener(this);
            this.mClickListener = mClickListener;
        }

        @Override
        public void onClick(View v) {
            if (mClickListener!=null){
                mClickListener.onItemClick(v,getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}
