package com.morecoin.app.mvp;

import com.morecoin.app.base.BasePresenterImpl;
import com.morecoin.app.base.observer.SimpleObserver;
import com.morecoin.app.bean.CoinMediaBean;
import com.morecoin.app.model.impl.BiMediaModelImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wxy on 2018/1/12.
 */

public class MediaCoinImpl extends BasePresenterImpl<MediaCoinContract.MediaCoinIView> implements MediaCoinContract.MediaCoinPresenter {
    @Override
    public void detachView() {

    }

    @Override
    public void onRefresh() {
        BiMediaModelImpl.getInstance().getMediaList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SimpleObserver<CoinMediaBean>() {
                    @Override
                    public void onNext(CoinMediaBean value) {
                        if (mView != null) {
                            mView.onRefreshOver();
                            mView.onBindData(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.onRefreshOver();
                        }
                    }
                });
    }
}
