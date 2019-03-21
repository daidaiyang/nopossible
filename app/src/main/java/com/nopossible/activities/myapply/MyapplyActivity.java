package com.nopossible.activities.myapply;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.MyApplyImageAdapter;
import com.nopossible.dialog.MyApplyTypeDialog;
import com.nopossible.dialog.PicSelecterDialog;
import com.nopossible.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyapplyActivity extends MVPBaseActivity<MyapplyContract.View, MyapplyPresenter> implements MyapplyContract.View, MyApplyImageAdapter.OnItemClickListener {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.myapply_selectType)
    LinearLayout myapplySelectType;
    @BindView(R.id.myapply_type)
    EditText myapplyType;
    @BindView(R.id.myapply_brand)
    EditText myapplyBrand;
    @BindView(R.id.myapply_recy)
    RecyclerView myapplyRecy;
    @BindView(R.id.myapply_phone)
    EditText myapplyPhone;
    @BindView(R.id.myapply_reason)
    EditText myapplyReason;
    @BindView(R.id.myapply_commit)
    TextView myapplyCommit;

    private List<String> mData;
    private MyApplyImageAdapter mImageAdapter;

    private MyApplyTypeDialog mDialog;

    private PicSelecterDialog mPicDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myapply);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("我要申购");
        titleRight.setVisibility(View.GONE);
        mData = new ArrayList<>();
        mImageAdapter = new MyApplyImageAdapter(getContext(), mData);
        myapplyRecy.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        myapplyRecy.setAdapter(mImageAdapter);
        mImageAdapter.setOnItemClick(this);
    }

    @OnClick({R.id.title_back, R.id.myapply_commit,R.id.myapply_selectType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                MyapplyActivity.this.finish();
                break;
            case R.id.myapply_commit:
                break;
            case R.id.myapply_selectType:
                if (mDialog == null){
                    mDialog = new MyApplyTypeDialog(getContext());
                }
                mDialog.show();
                break;
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        if (mData.size() ==0||position ==mData.size()){
            if (mPicDialog ==null){
                mPicDialog = new PicSelecterDialog(getContext());
            }
            mPicDialog.show();
        }
    }
}
