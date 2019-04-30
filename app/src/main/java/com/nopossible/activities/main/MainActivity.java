package com.nopossible.activities.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.nopossible.R;
import com.nopossible.activities.main.first.FirstFragment;
import com.nopossible.activities.main.mine.MineFragment;
import com.nopossible.activities.main.order.OrderFragment;
import com.nopossible.activities.main.shopping.ShoppingFragment;
import com.nopossible.mvp.MVPBaseActivity;
import com.nopossible.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View, ViewPager.OnPageChangeListener {




    @BindView(R.id.bottomnavigation)
    BottomNavigationView navigation;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;

    private MenuItem menuItem;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        mPresenter.requestPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        fragments.add(new FirstFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new OrderFragment());
        fragments.add(new MineFragment());
        viewPager.setAdapter(new PagerAdapter(fragmentManager,fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
    }

    @Override
    public void exitApp() {
        MainActivity.this.finish();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_shopping:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_order:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_mine:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            navigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = navigation.getMenu().getItem(i);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
     /*   //禁止ViewPager滑动
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/
}
