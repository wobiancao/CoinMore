package com.morecoin.app.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by wxy on 2017/12/28.
 */

public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppActivityManager.getInstance().add(this);

        if (getLayoutViewId() != -1) {
            setContentView(getLayoutViewId());
            ButterKnife.bind(this);
        }
        mPresenter = initInjector();
        attachView();
        initView(savedInstanceState);
    }

    public void initView(Bundle savedInstanceState){

    }

    //绑定view
    protected abstract int getLayoutViewId();
    /**
     * P层绑定   若无则返回null;
     *
     * @return
     */
    protected abstract T initInjector();

    /**
     * P层绑定V层
     */
    private void attachView() {
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    /**
     * P层解绑V层
     */
    private void detachView() {
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
        AppActivityManager.getInstance().remove(this);
        ButterKnife.unbind(this);
    }

    ////////////////////////////////启动Activity转场动画/////////////////////////////////////////////

    protected void startActivityForResultByAnim(Intent intent, int requestCode, int animIn, int animExit) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(animIn, animExit);
    }

    protected void startActivityByAnim(Intent intent, int animIn, int animExit) {
        startActivity(intent);
        overridePendingTransition(animIn, animExit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
