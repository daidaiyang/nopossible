package com.nopossible.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.OnItemClickListener;
import com.nopossible.adapter.PeisongItemAdapter;
import com.nopossible.entity.beans.BaseSelected;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeisongDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.select_others_rb)
    RadioButton selectOthersRb;
    @BindView(R.id.select_others)
    LinearLayout selectOthers;
    @BindView(R.id.confirm)
    TextView confirm;

    private PeisongItemAdapter mAdapter;
    private List<BaseSelected> mData;

    private OnDialogClick onDialogClick;

    private int myPosition = 0;

    public void setOnDialogClick(OnDialogClick onDialogClick) {
        this.onDialogClick = onDialogClick;
    }

    public void setmData(List<BaseSelected> mData) {
        this.mData = mData;
        this.mData.clear();
        this.mData.addAll(mData);
        if (mAdapter !=null)
        mAdapter.notifyDataSetChanged();
    }

    public PeisongDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_peisong);
        ButterKnife.bind(this);
        mData = new ArrayList<>();
        mData.add(new BaseSelected());
        mData.add(new BaseSelected());
        mData.add(new BaseSelected());
        mAdapter = new PeisongItemAdapter(getContext(),mData);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(mAdapter);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeisongDialog.this.cancel();
            }
        });
        confirm.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectOthersRb.setChecked(false);
                myPosition = position;
                BaseSelected baseSelected = mData.get(position);
                baseSelected.setSelected(true);
                List<BaseSelected> mList = new ArrayList<>();
                mList.addAll(mData);
                mList.get(position).setSelected(true);
                mData.clear();
                mData.addAll(mList);
                mAdapter.notifyDataSetChanged();
            }
        });
        selectOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BaseSelected> mList = new ArrayList<>();
                for (BaseSelected base:mData
                     ) {
                        base.setSelected(false);
                        mList.add(base);
                }
                mData.clear();
                mData.addAll(mList);
                myPosition= mData.size();
                mAdapter.notifyDataSetChanged();
            }
        });

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(attributes);
    }

    @Override
    public void onClick(View v) {
        if (onDialogClick !=null){
            onDialogClick.onConfirmClick(v,myPosition);
        }

    }


    public interface OnDialogClick{
        void onConfirmClick(View v,int position);
    }
}
