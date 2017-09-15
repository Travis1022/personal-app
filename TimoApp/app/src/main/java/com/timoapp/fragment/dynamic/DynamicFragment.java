package com.timoapp.fragment.dynamic;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.timoapp.R;
import com.timoapp.adapter.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 动态
 * Created by txw on 2017/8/31.
 */

public class DynamicFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.vp_dynamic)
    ViewPager mVpDynamic;
    Unbinder unbinder;
    @BindView(R.id.tab_dynamic)
    TabLayout mTabDynamic;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;

    private String[] tabTitle = {"推荐", "关注"};

    private MyFragmentAdapter mFragmentAdapter;

    private List<Fragment> mFragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dynamic, container, false);
        unbinder = ButterKnife.bind(this, rootView);


        //ViewPager + TabLayout
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new RecommendFragment());
        mFragmentList.add(new AttentionFragment());
        mFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), tabTitle, mFragmentList);
        mVpDynamic.setAdapter(mFragmentAdapter);

        mTabDynamic.setupWithViewPager(mVpDynamic);
        mTabDynamic.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_add)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击发布动态
            case R.id.iv_add:
                Toast.makeText(getActivity(), "您点击了发布动态按钮", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
