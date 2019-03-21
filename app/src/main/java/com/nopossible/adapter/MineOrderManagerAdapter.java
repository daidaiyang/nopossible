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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineOrderManagerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MineOrderManagerAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_ordermanager_item, viewGroup, false);
        return new ViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.itemRoot, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x20),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x15),
                0, 0);
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_avar)
        ImageView itemAvar;
        @BindView(R.id.item_statusInfo)
        TextView itemStatusInfo;
        @BindView(R.id.item_img1)
        ImageView itemImg1;
        @BindView(R.id.item_img2)
        ImageView itemImg2;
        @BindView(R.id.item_img3)
        ImageView itemImg3;
        @BindView(R.id.item_img4)
        ImageView itemImg4;
        @BindView(R.id.item_price)
        TextView itemPrice;
        @BindView(R.id.item_num)
        TextView itemNum;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_leftTime)
        TextView itemLeftTime;
        @BindView(R.id.item_again)
        TextView itemAgain;
        @BindView(R.id.item_operation)
        TextView itemOperation;
        @BindView(R.id.item_root)
        LinearLayout itemRoot;

        private OnItemClickListener itemClickListener;

        ViewHolder(View view,OnItemClickListener itemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.itemClickListener = itemClickListener;
            view.setOnClickListener(this);
            itemAgain.setOnClickListener(this);
            itemOperation.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener!=null){
                switch (v.getId()){
                    case R.id.item_again:
                        itemClickListener.onAgainClick(v,getPosition());
                        break;
                    case R.id.item_operation:
                        itemClickListener.onOperationClick(v,getPosition());
                        break;
                        default:
                        itemClickListener.onItemClick(v,getPosition());
                            break;
                }
            }

        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onAgainClick(View v,int position);
        void onOperationClick(View v,int position);
    }
}
