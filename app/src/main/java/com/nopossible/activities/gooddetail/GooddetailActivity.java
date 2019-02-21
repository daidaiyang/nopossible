package com.nopossible.activities.gooddetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.mvp.MVPBaseActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTxt.setText("商品详情");
        images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550748726961&di=676d2c85e0f9d52608a4a2db65176244&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-01%2F5a20cb9f0ee6a.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550748726960&di=2fee0312ac524eec071ef6162e86b74a&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-01%2F5a20cba4c5c8c.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550748726960&di=cea0821666f0a36322d5ac360cd334ad&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-13%2F5a31137e18403.jpg");
        //设置banner样式
        gooddetailBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        gooddetailBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        gooddetailBanner.setImages(images);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        gooddetailBanner.isAutoPlay(true);
        //设置轮播时间
        gooddetailBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        gooddetailBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        gooddetailBanner.start();
    }

    @OnClick({R.id.gooddetail_banner, R.id.gooddetail_addcart,R.id.title_back, R.id.title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gooddetail_banner:
                break;
            case R.id.gooddetail_addcart:
                break;
            case R.id.title_back:
                GooddetailActivity.this.finish();
                break;
            case R.id.title_right:
                break;
        }
    }
}
