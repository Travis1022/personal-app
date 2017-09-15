package com.timoapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ViewPager(含有Fragment)滑动适配器
 * Created by txw on 2017/8/31.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitle;
    private List<Fragment> mFragments;

    public MyFragmentAdapter(FragmentManager fm, String[] title, List<Fragment> fragments) {
        super(fm);
        this.mTitle = title;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
