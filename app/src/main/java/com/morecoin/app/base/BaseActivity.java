package com.morecoin.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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
}
