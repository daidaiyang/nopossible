package com.nopossible.activities.minegroup.myinfo;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nopossible.R;
import com.nopossible.activities.changepassword.ChangepasswordActivity;
import com.nopossible.dialog.PicSelecterDialog;
import com.nopossible.entity.beans.UpLoadImagebean;
import com.nopossible.entity.beans.UserDetail;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.GlideImageLoader_gallery;
import com.nopossible.utils.IntentUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyinfoActivity extends MVPBaseActivity<MyinfoContract.View, MyinfoPresenter> implements MyinfoContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.image_rl)
    RelativeLayout imageRl;
    @BindView(R.id.nickname_rl)
    RelativeLayout nicknameRl;
    @BindView(R.id.changepass_rl)
    RelativeLayout changepassRl;

    private PicSelecterDialog picSelecterDialog;
    private GalleryConfig galleryConfig;

    private List<String> pathList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setSuccessImg(UpLoadImagebean bean) {
        Glide.with(this)
                .load("http://121.43.169.100:8099/"+bean.getUrl())
                .into(image);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void omMsgEvent(UserDetail userDetail) {
        Glide.with(this)
                .load(userDetail.getHead_img_url())
                .into(image);
        nickname.setText(userDetail.getNick_name());
    }

    private void initView() {
        titleTxt.setText("个人资料");
        titleRight.setVisibility(View.GONE);
        picSelecterDialog = new PicSelecterDialog(getContext());
        picSelecterDialog.setTitleName("上传头像");
        picSelecterDialog.setOnPicDialogClick(onPicDialogClick);
        pathList.clear();
    }

    private void requestPermission(){
        new RxPermissions(this)
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            picSelecterDialog.show();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.title_back, R.id.image_rl, R.id.nickname_rl, R.id.changepass_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                MyinfoActivity.this.finish();
                break;
            case R.id.image_rl:
                requestPermission();
                break;
            case R.id.nickname_rl:
                break;
            case R.id.changepass_rl:
                IntentUtil.startActivity(this,ChangepasswordActivity.class);
                break;
        }
    }


    private PicSelecterDialog.OnPicDialogClick onPicDialogClick = new PicSelecterDialog.OnPicDialogClick() {
        @Override
        public void onFromCamera(View view) {
            picSelecterDialog.cancel();
            pathList.clear();
            galleryConfig = new GalleryConfig.Builder()
                    .imageLoader(new GlideImageLoader_gallery())    // ImageLoader 加载框架（必填）
                    .provider("com.nopossible.fileprovider")   // provider (必填)
                    .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                    .filePath("/Pictures")          // 图片存放路径   （选填）
                    .isOpenCamera(true)                  // 直接开启相机的标识位
                    .build();
            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(MyinfoActivity.this);
        }

        @Override
        public void onFromPic(View view) {
            picSelecterDialog.cancel();
            pathList.clear();
            galleryConfig = new GalleryConfig.Builder()
                    .imageLoader(new GlideImageLoader_gallery())    // ImageLoader 加载框架（必填）
                    .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                    .provider("com.nopossible.fileprovider")   // provider (必填)
                    .pathList(pathList)                         // 记录已选的图片
                    .multiSelect(false)                      // 是否多选   默认：false
                    .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                    .filePath("/Pictures")          // 图片存放路径
                    .build();
            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(MyinfoActivity.this);
        }
    };


    private IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
        }

        @Override
        public void onSuccess(List<String> photoList) {
            pathList.addAll(photoList);
            mPresenter.upDataImage(photoList.get(0));
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onError() {

        }
    };
}
