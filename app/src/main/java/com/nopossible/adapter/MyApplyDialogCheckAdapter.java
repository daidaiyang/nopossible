package com.nopossible.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.nopossible.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyApplyDialogCheckAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener onItemClick;

    public void setOnItemClick(OnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public MyApplyDialogCheckAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_myapply_typedialog_item, viewGroup, false);
        return new ViewHolder(view,onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.myapply_typedialog_check)
        CheckBox myapplyTypedialogCheck;

        private OnItemClickListener onItemClick;

        ViewHolder(View view,OnItemClickListener onItemClick) {
            super(view);
            ButterKnife.bind(this, view);
            this.onItemClick = onItemClick;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClick!=null){
                onItemClick.onItemClick(v,getPosition());
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
