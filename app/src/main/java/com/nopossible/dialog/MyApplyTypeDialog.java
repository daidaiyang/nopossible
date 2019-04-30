package com.nopossible.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.MyApplyDialogCheckAdapter;
import com.nopossible.entity.beans.ProductKindBean;
import com.nopossible.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyApplyTypeDialog extends Dialog {

    @BindView(R.id.dialog_myapply_title)
    TextView dialogMyapplyTitle;
    @BindView(R.id.dialog_myapply_close)
    ImageView dialogMyapplyClose;
    @BindView(R.id.dialog_myapply_recy)
    RecyclerView dialogMyapplyRecy;
    private Context mContext;

    private MyApplyDialogCheckAdapter mAdapter;
    private List<ProductKindBean> mData;

    private OnDialogItemClickListener onDialogItemClickListener;

    public void setOnDialogItemClickListener(OnDialogItemClickListener onDialogItemClickListener) {
        this.onDialogItemClickListener = onDialogItemClickListener;
    }

    public void setmData(List<ProductKindBean> mData) {
        this.mData = mData;
        if (mAdapter != null){
            this.mData.clear();
            this.mData.addAll(mData);
            mAdapter.notifyDataSetChanged();
        }
    }

    public MyApplyTypeDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        mContext = context;
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myapply_select);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        dialogMyapplyRecy.setLayoutManager(new GridLayoutManager(mContext,4));
        mAdapter = new MyApplyDialogCheckAdapter(mContext,mData);
        dialogMyapplyRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClick(onItemclick);
    }


    private MyApplyDialogCheckAdapter.OnItemClickListener onItemclick = new MyApplyDialogCheckAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if (onDialogItemClickListener !=null){
                onDialogItemClickListener.onItemClick(v,mData.get(position).getCode(),mData.get(position).getName());
            }
        }
    };


    @OnClick(R.id.dialog_myapply_close)
    public void onViewClicked() {
        MyApplyTypeDialog.this.cancel();
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity= Gravity.BOTTOM;
        layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height= (int) mContext.getResources().getDimension(R.dimen.x450);
        getWindow().setAttributes(layoutParams);
    }

    public interface  OnDialogItemClickListener{
        void onItemClick(View view,String code,String name);
    }
}
