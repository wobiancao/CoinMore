package com.morecoin.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseFragment;
import com.morecoin.app.bean.InfoBean;
import com.morecoin.app.mvp.InfoImpl;
import com.morecoin.app.mvp.InfoContract;
import com.morecoin.app.ui.adapter.InfoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * Created by wxy on 2017/12/28.
 * 讯息
 */

public class InfoFragment extends BaseFragment<InfoContract.InfoPresenter> implements InfoContract.InfoIView, OnRefreshListener {

    @Bind(R.id.info_list)
    RecyclerView mRecycler;
    @Bind(R.id.info_smartrefresh)
    SmartRefreshLayout mRefresh;
    @Bind(R.id.info_time)
    TextView mTimeView;
    private InfoAdapter mInfoAdapter;
    private String mHost = "";
    private final static String TAG_HOST = "tagHost";
    private Timer timer = new Timer(true);
    private boolean isTimer = false;
    private final long timeTask = 30 * 1000;
    @Override
    protected InfoContract.InfoPresenter initInjector() {
        return new InfoImpl();
    }

    public static InfoFragment newInstance(String host) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(TAG_HOST, host);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHost = getArguments().getString(TAG_HOST);
        }
    }

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mInfoAdapter = new InfoAdapter(null);
        mRefresh.setOnRefreshListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setNestedScrollingEnabled(false);
        mRecycler.setAdapter(mInfoAdapter);
        mRefresh.autoRefresh();
    }

    TimerTask task = new TimerTask() {
        public void run() {
            //每次需要执行的代码放到这里面。
            if (mPresenter != null){
                mPresenter.onParseInfo(mHost);
            }
        }
    };

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (mPresenter != null){
            mPresenter.onParseInfo(mHost);
        }
    }

    @Override
    public void onRefreshOver() {
        if (mRefresh != null){
            mRefresh.finishRefresh();
        }
    }

    @Override
    public void onBindData(InfoBean infoBean) {
        if (infoBean != null){
            if (!isTimer){//第一次进来之后 每隔10s请求一次
                isTimer = true;
                timer.schedule(task, new Date(), timeTask);
            }
            String mDate = infoBean.mDate + "";
            if (mTimeView != null){
                mTimeView.setText(mDate);
            }
            if (mInfoAdapter != null){
                mInfoAdapter.setNewData(infoBean.mData);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null){
            task.cancel();
            timer.cancel();
        }
    }
}
