package com.morecoin.app.ui;

import android.support.v4.view.ViewPager;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.morecoin.app.R;
import com.morecoin.app.base.BaseActivity;
import com.morecoin.app.base.IPresenter;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.tab_layout)
    DachshundTabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

}
