package com.morecoin.app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.kekstudio.dachshundtablayout.indicators.DachshundIndicator;
import com.morecoin.app.R;
import com.morecoin.app.base.BaseActivity;
import com.morecoin.app.base.IPresenter;
import com.morecoin.app.model.impl.BiCaijingModelImpl;
import com.morecoin.app.model.impl.BiShiJieModelImpl;
import com.morecoin.app.ui.fragment.InfoFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.tab_layout)
    DachshundTabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    MyPagerAdapter mMyPagerAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] names = new String[]{"币财经", "币世界"};
    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mFragments.add(InfoFragment.newInstance(BiCaijingModelImpl.HOST));
        mFragments.add(InfoFragment.newInstance(BiShiJieModelImpl.HOST));
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setAnimatedIndicator(new DachshundIndicator(mTabLayout));
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return names[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
