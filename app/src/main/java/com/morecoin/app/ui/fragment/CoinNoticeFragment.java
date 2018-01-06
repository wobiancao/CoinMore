package com.morecoin.app.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseFragment;
import com.morecoin.app.bean.CoinNoticeBean;
import com.morecoin.app.mvp.NoticeCoinContract;
import com.morecoin.app.mvp.NoticeCoinImpl;
import com.morecoin.app.ui.adapter.CoinNoticeAdapter;
import com.morecoin.app.utils.DateUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;

/**
 * Created by wxy on 2018/1/4.
 */

public class CoinNoticeFragment extends BaseFragment<NoticeCoinContract.NoticeCoinPresenter> implements NoticeCoinContract.NoticeCoinIView, OnRefreshListener{
        @Bind(R.id.info_list)
    RecyclerView mRecycler;
    @Bind(R.id.info_smartrefresh)
    SmartRefreshLayout mRefresh;
   @Bind(R.id.info_time)
    TextView mTimeView;
    private CoinNoticeAdapter mUpCoinAdapter;
    @Override
    protected NoticeCoinContract.NoticeCoinPresenter initInjector() {
        return new NoticeCoinImpl();
    }

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTimeView.setText(DateUtils.getNowTimeStr() + "");
        mUpCoinAdapter = new CoinNoticeAdapter(null);
        mRefresh.setOnRefreshListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setNestedScrollingEnabled(false);
        mRecycler.setAdapter(mUpCoinAdapter);
        mRefresh.autoRefresh();
    }


    @Override
    public void onRefreshOver() {
        if (mRefresh != null) {
            mRefresh.finishRefresh();
        }
    }

    @Override
    public void onBindData(CoinNoticeBean data) {
        if (mUpCoinAdapter != null) {
            if (data != null && data.Status.equals("success")){
                if (data.NoticeList != null){
                    mUpCoinAdapter.setNewData(data.NoticeList);
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
