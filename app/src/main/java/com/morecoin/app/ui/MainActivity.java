package com.morecoin.app.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.kekstudio.dachshundtablayout.indicators.DachshundIndicator;
import com.morecoin.app.R;
import com.morecoin.app.base.BaseActivity;
import com.morecoin.app.base.IPresenter;
import com.morecoin.app.model.impl.BiCaijingModelImpl;
import com.morecoin.app.model.impl.BiKnowModelImpl;
import com.morecoin.app.model.impl.BiShiJieModelImpl;
import com.morecoin.app.ui.fragment.CoinInfoFragment;
import com.morecoin.app.ui.fragment.CoinNoticeFragment;
import com.morecoin.app.ui.fragment.CoinUpFragment;
import com.morecoin.app.utils.VersionUtil;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.tab_layout)
    DachshundTabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    MyPagerAdapter mMyPagerAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] names = new String[]{"币财经", "币世界", "币知道", "币公告", "币上新"};

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
        mFragments.add(CoinInfoFragment.newInstance(BiCaijingModelImpl.HOST));
        mFragments.add(CoinInfoFragment.newInstance(BiShiJieModelImpl.HOST));
        mFragments.add(CoinInfoFragment.newInstance(BiKnowModelImpl.HOST));
        mFragments.add(new CoinNoticeFragment());
        mFragments.add(new CoinUpFragment());
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setAnimatedIndicator(new DachshundIndicator(mTabLayout));
        PgyUpdateManager.setIsForced(true);
        PgyUpdateManager.register(this, getString(R.string.provider_file), new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {

            }

            @Override
            public void onUpdateAvailable(final String result) {
                final AppBean appBean = getAppBeanFromString(result);
                int code = Integer.valueOf(appBean.getVersionCode());
                if (code > VersionUtil.getVersionCode(MainActivity.this)){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("新版本提示")
                            .setMessage(appBean.getReleaseNote())
                            .setNegativeButton(
                                    "确定",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            Uri uri = Uri.parse("https://www.pgyer.com/morecoin");
                                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                            startActivity(intent);
                                        }
                                    }).show();
                }
            }

        });
    }


    @OnClick(R.id.image_more)
    public void onViewClicked() {
        startActivityByAnim(new Intent(MainActivity.this, AboutActivity.class), android.R.anim.fade_in, android.R.anim.fade_out);

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

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

}
