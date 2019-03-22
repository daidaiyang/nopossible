package com.nopossible.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.adapter.ConfirmRecepitDialogAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmRecepitDialog extends Dialog {

    @BindView(R.id.dialog_close)
    ImageView dialogClose;
    @BindView(R.id.dialog_normal_num1)
    TextView normalNum1;
    @BindView(R.id.dialog_normal_num2)
    TextView normalNum2;
    @BindView(R.id.dialog_normal_txt)
    TextView normalTxt;
    @BindView(R.id.dialog_normal_img)
    ImageView normalImg;
    @BindView(R.id.dialog_normal)
    LinearLayout normal;
    @BindView(R.id.dialog_normal_recy)
    RecyclerView normalRecy;
    @BindView(R.id.dialog_back_num1)
    TextView backNum1;
    @BindView(R.id.dialog_back_num2)
    TextView backNum2;
    @BindView(R.id.dialog_back_txt)
    TextView backTxt;
    @BindView(R.id.dialog_back_img)
    ImageView backImg;
    @BindView(R.id.dialog_back)
    LinearLayout back;
    @BindView(R.id.dialog_back_recy)
    RecyclerView backRecy;
    @BindView(R.id.dialog_need_num1)
    TextView needNum1;
    @BindView(R.id.dialog_need_num2)
    TextView needNum2;
    @BindView(R.id.dialog_need_txt)
    TextView needTxt;
    @BindView(R.id.dialog_need_img)
    ImageView needImg;
    @BindView(R.id.dialog_need)
    LinearLayout need;
    @BindView(R.id.dialog_need_recy)
    RecyclerView needRecy;
    @BindView(R.id.dialog_order_price)
    TextView orderPrice;
    @BindView(R.id.dialog_back_price)
    TextView backPrice;
    @BindView(R.id.dialog_add_price)
    TextView addPrice;
    @BindView(R.id.dialog_total_price)
    TextView totalPrice;
    @BindView(R.id.dialog_payagain_num)
    TextView payagainNum;
    @BindView(R.id.dialog_confirm_order)
    TextView confirmOrder;
    @BindView(R.id.dialog_normal_check)
    CheckBox dialogNormalCheck;
    @BindView(R.id.dialog_back_check)
    CheckBox dialogBackCheck;
    @BindView(R.id.dialog_need_check)
    CheckBox dialogNeedCheck;


    private ConfirmRecepitDialogAdapter mNormalAdapter;
    private ConfirmRecepitDialogAdapter mBackAdapter;
    private ConfirmRecepitDialogAdapter mNeedAdapter;

    private List<String> mNormalData;
    private List<String> mBackData;
    private List<String> mNeedData;


    public ConfirmRecepitDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        setCanceledOnTouchOutside(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirmrecepit);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mNormalData = new ArrayList<>();
        mNormalData.add("");
        mNormalData.add("");
        mNormalData.add("");
        mNormalData.add("");
        mBackData = new ArrayList<>();
        mBackData.add("");
        mBackData.add("");
        mBackData.add("");
        mBackData.add("");
        mNeedData = new ArrayList<>();
        mNeedData.add("");
        mNeedData.add("");
        mNeedData.add("");
        mNeedData.add("");
        mNormalAdapter = new ConfirmRecepitDialogAdapter(getContext(), mNormalData);
        mBackAdapter = new ConfirmRecepitDialogAdapter(getContext(), mBackData);
        mNeedAdapter = new ConfirmRecepitDialogAdapter(getContext(), mNeedData);
        normalRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        backRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        needRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        normalRecy.setAdapter(mNeedAdapter);
        backRecy.setAdapter(mBackAdapter);
        needRecy.setAdapter(mNeedAdapter);
    }

    @OnClick({R.id.dialog_close, R.id.dialog_normal, R.id.dialog_back, R.id.dialog_need, R.id.dialog_confirm_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_close:
                ConfirmRecepitDialog.this.cancel();
                break;
            case R.id.dialog_normal:
                dialogNormalCheck.setChecked(!dialogNormalCheck.isChecked());
                if (dialogNormalCheck.isChecked()){
                    normalRecy.setVisibility(View.VISIBLE);
                    normalTxt.setText("收起详情");
                    normalImg.setImageResource(R.drawable.jiantou_expand_shang);
                }else {
                    normalRecy.setVisibility(View.GONE);
                    normalTxt.setText("展开详情");
                    normalImg.setImageResource(R.drawable.jiantou_expand_xia);
                }
                break;
            case R.id.dialog_back:
                dialogBackCheck.setChecked(!dialogBackCheck.isChecked());
                if (dialogBackCheck.isChecked()){
                    backRecy.setVisibility(View.VISIBLE);
                    backTxt.setText("收起详情");
                    backImg.setImageResource(R.drawable.jiantou_expand_shang);
                }else {
                    backRecy.setVisibility(View.GONE);
                    backTxt.setText("展开详情");
                    backImg.setImageResource(R.drawable.jiantou_expand_xia);
                }
                break;
            case R.id.dialog_need:
                dialogNeedCheck.setChecked(!dialogNeedCheck.isChecked());
                if (dialogNeedCheck.isChecked()){
                    needRecy.setVisibility(View.VISIBLE);
                    needTxt.setText("收起详情");
                    needImg.setImageResource(R.drawable.jiantou_expand_shang);
                }else {
                    needRecy.setVisibility(View.GONE);
                    needTxt.setText("展开详情");
                    needImg.setImageResource(R.drawable.jiantou_expand_xia);
                }
                break;
            case R.id.dialog_confirm_order:
                break;
        }
    }


    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.height = (int) getContext().getResources().getDimension(R.dimen.y980);
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(attributes);
    }
}
