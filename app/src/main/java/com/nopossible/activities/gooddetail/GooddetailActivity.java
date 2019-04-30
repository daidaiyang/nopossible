package com.nopossible.activities.gooddetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.entity.beans.BaseImageList;
import com.nopossible.entity.beans.ProductListBean;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.AppUtil;
import com.nopossible.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GooddetailActivity extends MVPBaseActivity<GooddetailContract.View, GooddetailPresenter> implements GooddetailContract.View {


    @BindView(R.id.gooddetail_banner)
    Banner gooddetailBanner;
    @BindView(R.id.gooddetail_title)
    TextView gooddetailTitle;
    @BindView(R.id.gooddetail_price)
    TextView gooddetailPrice;
    @BindView(R.id.gooddetail_standar)
    TextView gooddetailStandar;
    @BindView(R.id.gooddetail_addcart)
    TextView gooddetailAddcart;
    @BindView(R.id.gooddetail_name)
    TextView gooddetailName;
    @BindView(R.id.gooddetail_peiliao)
    TextView gooddetailPeiliao;
    @BindView(R.id.gooddetail_num)
    TextView gooddetailNum;
    @BindView(R.id.gooddetail_webview)
    WebView gooddetailWebview;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;

    private List<String> images;
    private ProductListBean productListBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        productListBean = (ProductListBean) getIntent().getExtras().getSerializable("product");
        gooddetailBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        gooddetailBanner.setImageLoader(new GlideImageLoader());
        gooddetailBanner.isAutoPlay(true);
        gooddetailBanner.setDelayTime(1500);
        gooddetailBanner.setIndicatorGravity(BannerConfig.RIGHT);
        titleTxt.setText("商品详情");
        titleRight.setVisibility(View.GONE);
        images = new ArrayList<>();
        List<BaseImageList> images_list = productListBean.getImages_list();
        for (int i=0;i<images_list.size();i++){
            images.add(images_list.get(i).getUrl());
        }
        //设置图片集合
        gooddetailBanner.setImages(images);
        gooddetailBanner.start();
    }

    private void initData(){
        gooddetailTitle.setText(productListBean.getName());
        gooddetailPrice.setText("￥"+AppUtil.get2xiaoshu(productListBean.getSell_price()));
        gooddetailStandar.setText(productListBean.getSpec());
        gooddetailName.setText(productListBean.getBrand());
        gooddetailPeiliao.setText(productListBean.getKind_code());
        gooddetailNum.setText(productListBean.getCo());
    }

    @OnClick({R.id.gooddetail_banner, R.id.gooddetail_addcart,R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gooddetail_banner:

                break;
            case R.id.gooddetail_addcart:
                mPresenter.addtocart(productListBean.getId());
                break;
            case R.id.title_back:
                GooddetailActivity.this.finish();
                break;
        }
    }
}
