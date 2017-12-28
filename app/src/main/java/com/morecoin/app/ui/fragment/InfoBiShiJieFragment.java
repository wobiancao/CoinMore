package com.morecoin.app.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseFragment;
import com.morecoin.app.mvp.BiShiJieImpl;
import com.morecoin.app.mvp.BiShijieContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wxy on 2017/12/28.
 * 币世界讯息
 */

public class InfoBiShiJieFragment extends BaseFragment<BiShijieContract.BiShiJiePresenter> implements BiShijieContract.IBiShiJieView {

    @Bind(R.id.bishijie_list)
    RecyclerView mRecycler;
    @Bind(R.id.bishijie_smartrefresh)
    SmartRefreshLayout mRefresh;

    @Override
    protected BiShijieContract.BiShiJiePresenter initInjector() {
        return new BiShiJieImpl();
    }

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_bishijie;
    }

}
