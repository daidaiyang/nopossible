package com.nopossible.activities.main.mine;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.mvp.MVPBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter> implements MineContract.View {


    @BindView(R.id.mine_message)
    ImageView mineMessage;
    @BindView(R.id.mine_setting)
    ImageView mineSetting;
    @BindView(R.id.mine_img)
    ImageView mineImg;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_tel)
    TextView mineTel;
    @BindView(R.id.mine_info)
    RelativeLayout mineInfo;
    @BindView(R.id.mine_score_num)
    TextView mineScoreNum;
    @BindView(R.id.mine_score)
    RelativeLayout mineScore;
    @BindView(R.id.mine_apply)
    RelativeLayout mineApply;
    @BindView(R.id.mine_address)
    RelativeLayout mineAddress;
    @BindView(R.id.mine_share)
    RelativeLayout mineShare;
    @BindView(R.id.mine_help)
    RelativeLayout mineHelp;
    @BindView(R.id.mine_us)
    RelativeLayout mineUs;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_message, R.id.mine_setting, R.id.mine_info, R.id.mine_score, R.id.mine_apply, R.id.mine_address, R.id.mine_share, R.id.mine_help, R.id.mine_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_message:
                break;
            case R.id.mine_setting:
                break;
            case R.id.mine_info:
                break;
            case R.id.mine_score:
                break;
            case R.id.mine_apply:
                break;
            case R.id.mine_address:
                break;
            case R.id.mine_share:
                break;
            case R.id.mine_help:
                break;
            case R.id.mine_us:
                break;
        }
    }
}
