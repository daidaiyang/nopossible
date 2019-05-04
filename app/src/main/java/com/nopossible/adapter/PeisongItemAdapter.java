package com.nopossible.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.entity.beans.BaseSelected;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeisongItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<BaseSelected> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PeisongItemAdapter(Context mContext, List<BaseSelected> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_peisong_select, viewGroup, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        BaseSelected baseSelected = mData.get(i);
        holder.radiobutton.setChecked(baseSelected.isSelected());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.name_type)
        TextView nameType;
        @BindView(R.id.radiobutton)
        RadioButton radiobutton;

        private OnItemClickListener onItemClickListener;


        ViewHolder(View view,OnItemClickListener onItemClickListener) {
            super(view);
            this.onItemClickListener = onItemClickListener;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener!=null){
                onItemClickListener.onItemClick(v,getPosition());
            }
        }
    }


}
