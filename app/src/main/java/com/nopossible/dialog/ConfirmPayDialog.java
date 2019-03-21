package com.nopossible.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmPayDialog extends Dialog {
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.payWechat_check)
    RadioButton payWechatCheck;
    @BindView(R.id.payWechat)
    RelativeLayout payWechat;
    @BindView(R.id.payali_check)
    RadioButton payaliCheck;
    @BindView(R.id.payali)
    RelativeLayout payali;
    @BindView(R.id.payNow)
    TextView payNow;

    public ConfirmPayDialog(@NonNull Context context) {
        super(context, R.style.BottomDialogStyle);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirmpay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.close, R.id.payWechat, R.id.payali, R.id.payNow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                ConfirmPayDialog.this.cancel();
                break;
            case R.id.payWechat:
                payWechatCheck.setChecked(!payWechatCheck.isChecked());
                payaliCheck.setChecked(false);
                break;
            case R.id.payali:
                payaliCheck.setChecked(!payaliCheck.isChecked());
                payWechatCheck.setChecked(false);
                break;
            case R.id.payNow:
                break;
        }
    }


    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(attributes);
    }
}
