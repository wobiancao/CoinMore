package com.morecoin.app.mvp;

import android.support.annotation.NonNull;

import com.morecoin.app.base.BasePresenterImpl;
import com.morecoin.app.base.IView;

/**
 * Created by wxy on 2017/12/28.
 */

public class BiShiJieImpl extends BasePresenterImpl<BiShijieContract.IBiShiJieView> implements BiShijieContract.BiShiJiePresenter {

    @Override
    public void attachView(@NonNull IView iView) {
        super.attachView(iView);

    }

    @Override
    public void detachView() {

    }
}
