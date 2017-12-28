package com.morecoin.app.base;

import android.support.annotation.NonNull;

public abstract class BasePresenterImpl<T extends IView> implements IPresenter{
    protected T mView;

    @Override
    public void attachView(@NonNull IView iView) {
        mView = (T) iView;
    }
}
