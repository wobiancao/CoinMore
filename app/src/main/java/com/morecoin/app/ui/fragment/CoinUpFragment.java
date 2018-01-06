package com.morecoin.app.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseFragment;
import com.morecoin.app.bean.CoinUpBean;
import com.morecoin.app.mvp.UpCoinContract;
import com.morecoin.app.mvp.UpCoinImpl;
import com.morecoin.app.ui.adapter.CoinUpAdapter;
import com.morecoin.app.utils.DateUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;

/**
 * Created by wxy on 2018/1/4.
 */

public class CoinUpFragment extends BaseFragment<UpCoinContract.UpCoinPresenter> implements UpCoinContract.UpCoinIView, OnRefreshListener{
        @Bind(R.id.info_list)
    RecyclerView mRecycler;
    @Bind(R.id.info_smartrefresh)
    SmartRefreshLayout mRefresh;
   @Bind(R.id.info_time)
    TextView mTimeView;
    private CoinUpAdapter mUpCoinAdapter;
    @Override
    protected UpCoinContract.UpCoinPresenter initInjector() {
        return new UpCoinImpl();
    }

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTimeView.setText(DateUtils.getNowTimeStr() + "");
        mUpCoinAdapter = new CoinUpAdapter(null);
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
    public void onBindData(CoinUpBean coinUpBean) {
        if (mUpCoinAdapter != null) {
            if (coinUpBean != null && coinUpBean.code == 0){
                if (coinUpBean.data != null){
                    mUpCoinAdapter.setNewData(coinUpBean.data.list);
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
