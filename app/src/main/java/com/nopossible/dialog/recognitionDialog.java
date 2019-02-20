package com.nopossible.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.nopossible.R;
import com.nopossible.customview.CircleImageView;

import butterknife.BindView;

public class recognitionDialog extends Dialog {

    @BindView(R.id.dialog_recognition_close)
    LinearLayout close;
    @BindView(R.id.dialog_recognition_circlr1)
    CircleImageView circlr1;
    @BindView(R.id.dialog_recognition_circlr2)
    CircleImageView circlr2;
    @BindView(R.id.dialog_recognition_circlr3)
    CircleImageView circlr3;
    @BindView(R.id.dialog_recognition_circlr4)
    CircleImageView circlr4;
    @BindView(R.id.dialog_recognition_circlr5)
    CircleImageView circlr5;
    @BindView(R.id.dialog_recognition_circlr6)
    CircleImageView circlr6;
    @BindView(R.id.dialog_recognition_circlr7)
    CircleImageView circlr7;
    @BindView(R.id.dialog_recognition_circlr8)
    CircleImageView circlr8;
    private Context mContext;

    private CloseListener closeListener;

    public recognitionDialog(Context context) {
        super(context, R.style.LoadingDialog);
        this.mContext = context;
        setView();
    }

    private void setView() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_recognition, null, false);
        this.setContentView(view);
    }

    public void setCloseListener(CloseListener closeListener){
            this.closeListener = closeListener;
    }

    public interface  CloseListener{
        void close();
    }
}
