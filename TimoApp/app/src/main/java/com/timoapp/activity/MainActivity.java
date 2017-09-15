package com.timoapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timoapp.R;
import com.timoapp.fragment.discover.DiscoverFragment;
import com.timoapp.fragment.dynamic.DynamicFragment;
import com.timoapp.fragment.msg.MsgFragment;
import com.timoapp.fragment.my.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主功能界面
 * create by txw on 2017-08-31
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.layout_dynamic)
    LinearLayout mLayoutDynamic;         //动态
    @BindView(R.id.layout_discover)
    LinearLayout mLayoutDiscover;        //发现
    @BindView(R.id.layout_msg)
    LinearLayout mLayoutMsg;             //消息
    @BindView(R.id.layout_my)
    LinearLayout mLayoutMy;              //我的
    @BindView(R.id.iv_dynamic)
    ImageView mIvDynamic;
    @BindView(R.id.tv_dynamic)
    TextView mTvDynamic;
    @BindView(R.id.iv_discover)
    ImageView mIvDiscover;
    @BindView(R.id.tv_discover)
    TextView mTvDiscover;
    @BindView(R.id.iv_msg)
    ImageView mIvMsg;
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.iv_my)
    ImageView mIvMy;
    @BindView(R.id.tv_my)
    TextView mTvMy;

    private List<Fragment> mFragmentList;
    private ImageView[] mImageViews = new ImageView[4];
    private TextView[] mTextViews = new TextView[4];
    private int[] resId = new int[]{R.mipmap.attention_selected, R.mipmap.discover_selected,
            R.mipmap.msg_selected, R.mipmap.my_selected};

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //ImageView
        mImageViews[0] = mIvDynamic;
        mImageViews[1] = mIvDiscover;
        mImageViews[2] = mIvMsg;
        mImageViews[3] = mIvMy;

        //TextView
        mTextViews[0] = mTvDynamic;
        mTextViews[1] = mTvDiscover;
        mTextViews[2] = mTvMsg;
        mTextViews[3] = mTvMy;

        //Fragment
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new DynamicFragment());
        mFragmentList.add(new DiscoverFragment());
        mFragmentList.add(new MsgFragment());
        mFragmentList.add(new MyFragment());

        //ViewPager
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        mVpMain.setAdapter(mPagerAdapter);
        mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTab();
                mImageViews[position].setImageResource(resId[position]);
                mTextViews[position].setTextColor(getResources().getColor(R.color.blue));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        mVpMain.addOnPageChangeListener(mOnPageChangeListener);

    }

    /**
     * 重置Tab栏
     */
    private void resetTab() {
        //重置TextView
        for (int i = 0; i < mTextViews.length; i++) {
            mTextViews[i].setTextColor(getResources().getColor(R.color.text_shallow));
        }
        //重置ImageView
        mImageViews[0].setImageResource(R.mipmap.attention);
        mImageViews[1].setImageResource(R.mipmap.discover);
        mImageViews[2].setImageResource(R.mipmap.msg);
        mImageViews[3].setImageResource(R.mipmap.my);
    }


    @OnClick({R.id.layout_dynamic, R.id.layout_discover, R.id.layout_msg, R.id.layout_my})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_dynamic:
                if (mVpMain.getCurrentItem() != 0) {
                    mVpMain.setCurrentItem(0);
                }
                break;
            case R.id.layout_discover:
                if (mVpMain.getCurrentItem() != 1) {
                    mVpMain.setCurrentItem(1);
                }
                break;
            case R.id.layout_msg:
                if (mVpMain.getCurrentItem() != 2) {
                    mVpMain.setCurrentItem(2);
                }
                break;
            case R.id.layout_my:
                if (mVpMain.getCurrentItem() != 3) {
                    mVpMain.setCurrentItem(3);
                }
                break;
        }
    }
}
