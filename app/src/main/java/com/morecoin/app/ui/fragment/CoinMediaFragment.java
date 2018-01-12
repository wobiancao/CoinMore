package com.morecoin.app.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseFragment;
import com.morecoin.app.bean.CoinMediaBean;
import com.morecoin.app.mvp.MediaCoinContract;
import com.morecoin.app.mvp.MediaCoinImpl;
import com.morecoin.app.ui.adapter.CoinMediaAdapter;
import com.morecoin.app.utils.DateUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;

/**
 * Created by wxy on 2018/1/4.
 */

public class CoinMediaFragment extends BaseFragment<MediaCoinContract.MediaCoinPresenter> implements MediaCoinContract.MediaCoinIView, OnRefreshListener {
    @Bind(R.id.info_list)
    RecyclerView mRecycler;
    @Bind(R.id.info_smartrefresh)
    SmartRefreshLayout mRefresh;
    @Bind(R.id.info_time)
    TextView mTimeView;
    private CoinMediaAdapter mMediaCoinAdapter;

    @Override
    protected MediaCoinContract.MediaCoinPresenter initInjector() {
        return new MediaCoinImpl();
    }

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTimeView.setText(DateUtils.getNowTimeStr() + "");
        mMediaCoinAdapter = new CoinMediaAdapter(null);
        mRefresh.setOnRefreshListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setNestedScrollingEnabled(false);
        mRecycler.setAdapter(mMediaCoinAdapter);
        mRefresh.autoRefresh();
    }


    @Override
    public void onRefreshOver() {
        if (mRefresh != null) {
            mRefresh.finishRefresh();
        }
    }

    @Override
    public void onBindData(CoinMediaBean data) {
        if (mMediaCoinAdapter != null) {
            if (data != null && data.code == 0) {
                if (data.data != null) {
                    mMediaCoinAdapter.setNewData(data.data.newsList);
                }
            }

        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (mPresenter != null) {
            mPresenter.onRefresh();
        }
    }

}
