package com.nopossible.activities.main.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossible.R;
import com.nopossible.activities.minegroup.myapply.MyapplyActivity;
import com.nopossible.activities.minegroup.myinfo.MyinfoActivity;
import com.nopossible.activities.minegroup.mymessage.MymessageActivity;
import com.nopossible.activities.minegroup.myscore.MyscoreActivity;
import com.nopossible.activities.minegroup.mysetting.MysettingActivity;
import com.nopossible.activities.myaddress.MyAddressActivity;
import com.nopossible.entity.beans.UserDetail;
import com.nopossible.mvp.MVPBaseFragment;
import com.nopossible.utils.IntentUtil;
import com.nopossible.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

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

    private UserDetail userDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mPresenter.getInfo();
    }

    @Override
    public void setUserData(UserDetail userDetail) {
        this.userDetail = userDetail;
        Glide.with(getContext())
                .load(userDetail.getHead_img_url())
                .into(mineImg);
        mineName.setText(userDetail.getNick_name());
        mineTel.setText(userDetail.getTel_phone());

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
                Intent messageIntent = new Intent(getContext(), MymessageActivity.class);
                messageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(messageIntent);
                break;
            case R.id.mine_setting:
                Intent settingIntent = new Intent(getContext(), MysettingActivity.class);
                settingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(settingIntent);
                break;
            case R.id.mine_info:
                EventBus.getDefault().postSticky(userDetail);
                Intent infoIntent = new Intent(getContext(), MyinfoActivity.class);
                infoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(infoIntent);
                break;
            case R.id.mine_score:
                Intent scoreIntent = new Intent(getContext(), MyscoreActivity.class);
                scoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(scoreIntent);
                break;
            case R.id.mine_apply:
                Intent applyIntent = new Intent(getContext(), MyapplyActivity.class);
                applyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(applyIntent);
                break;
            case R.id.mine_address:
                IntentUtil.startActivity(getContext(),MyAddressActivity.class);
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
