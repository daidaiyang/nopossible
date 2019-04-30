package com.nopossible.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nopossible.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyApplyImageAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<String> mData;
    private OnItemClickListener onItemClick;

    public void setOnItemClick(OnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public MyApplyImageAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_myapply_item_img, viewGroup, false);
        return new ViewHolder(view,onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (mData.size() == 0||mData.size() == i){
            Glide.with(mContext)
                    .load(R.drawable.addpic)
                    .into(holder.myapplyItemImg);
        }else{
            Glide.with(mContext)
                    .load(mData.get(i))
                    .into(holder.myapplyItemImg);
        }

    }

    @Override
    public int getItemCount() {
        return mData==null?1:(mData.size()>=5?5:mData.size()+1);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.myapply_item_img)
        ImageView myapplyItemImg;
        private OnItemClickListener onItemClick;

        ViewHolder(View view, OnItemClickListener onItemClick) {
            super(view);
            this.onItemClick = onItemClick;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClick!=null){
                onItemClick.onItemClick(v,getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}
