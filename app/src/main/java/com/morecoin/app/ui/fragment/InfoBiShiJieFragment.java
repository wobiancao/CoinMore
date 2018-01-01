package com.morecoin.app.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseFragment;
import com.morecoin.app.mvp.BiShiJieImpl;
import com.morecoin.app.mvp.BiShijieContract;
import com.morecoin.app.ui.adapter.InfoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;

/**
 * Created by wxy on 2017/12/28.
 * 币世界讯息
 */

public class InfoBiShiJieFragment extends BaseFragment<BiShijieContract.BiShiJiePresenter> implements BiShijieContract.IBiShiJieView, OnRefreshListener {

    @Bind(R.id.bishijie_list)
    RecyclerView mRecycler;
    @Bind(R.id.bishijie_smartrefresh)
    SmartRefreshLayout mRefresh;
    private InfoAdapter mInfoAdapter;
    @Override
    protected BiShijieContract.BiShiJiePresenter initInjector() {
        return new BiShiJieImpl();
    }

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_bishijie;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mInfoAdapter = new InfoAdapter(null);
        mRefresh.setOnRefreshListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mInfoAdapter);
        mRefresh.autoRefresh();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (mPresenter != null){
            mPresenter.onParseBiShijie();
        }
    }
}
