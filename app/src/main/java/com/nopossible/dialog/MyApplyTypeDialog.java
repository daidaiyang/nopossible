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
    private List<String> mData;

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
        mData = new ArrayList<>();
        mData.add(""); mData.add(""); mData.add(""); mData.add(""); mData.add(""); mData.add("");
        mData.add(""); mData.add(""); mData.add(""); mData.add(""); mData.add(""); mData.add("");
        mAdapter = new MyApplyDialogCheckAdapter(mContext,mData);
        dialogMyapplyRecy.setAdapter(mAdapter);
    }


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
}
