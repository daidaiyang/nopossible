package com.nopossible.activities.myaddress.addresslist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossible.R;
import com.nopossible.activities.myaddress.MyAddressEventBackBean;
import com.nopossible.adapter.MyAddressListAdapter;
import com.nopossible.mvp.MVPBaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddresslistFragment extends MVPBaseFragment<AddresslistContract.View, AddresslistPresenter> implements AddresslistContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_info)
    TextView emptyInfo;
    @BindView(R.id.empty)
    LinearLayout empty;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    MyAddressListAdapter mAdapter;
    private List<String> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myaddress_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titleRight.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        mPresenter.getAddressList();
        mData = new ArrayList<>();
        mAdapter = new MyAddressListAdapter(mData,getContext());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClick);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title_back, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                EventBus.getDefault().post(new MyAddressEventBackBean(0));
                break;
            case R.id.add:
                EventBus.getDefault().post(new MyAddressEventBackBean(2));
                break;
        }
    }


    private MyAddressListAdapter.OnItemClickListener onItemClick = new MyAddressListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

        }

        @Override
        public void onMorenClick(View view, int position) {

        }

        @Override
        public void onEditClick(View view, int position) {
        }

        @Override
        public void onDeleteClick(View view, int posotion) {

        }
    };
}
