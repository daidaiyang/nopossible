package com.nopossible.activities.splash;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nopossible.R;
import com.nopossible.activities.main.MainActivity;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.SpUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SplashActivity extends MVPBaseActivity<SplashContract.View, SplashPresenter> implements SplashContract.View {


    @BindView(R.id.splash_image)
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550579455560&di=bbb07866fc5b5997c361f1c64db73ec4&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201509%2F21%2F20150921015016_zfEk5.thumb.700_0.jpeg")
                .into(imageView);

        //创建Timer对象
        Timer timer = new Timer();
        //创建TimerTask对象

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        //使用timer.schedule（）方法调用timerTask，定时3秒后执行run
        timer.schedule(timerTask, 3000);
    }

}
