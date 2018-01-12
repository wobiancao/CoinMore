package com.morecoin.app.mvp;

import com.morecoin.app.base.BasePresenterImpl;
import com.morecoin.app.base.observer.SimpleObserver;
import com.morecoin.app.bean.CoinMediaDetailBean;
import com.morecoin.app.model.impl.BiMediaDetailModelImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wxy on 2018/1/12.
 */

public class MediaDetailImpl extends BasePresenterImpl<MediaDetailContract.MediaDetailIView> implements MediaDetailContract.MediaDetailPresenter {
    @Override
    public void detachView() {

    }

    @Override
    public void onGetDetail(String newsId) {
        BiMediaDetailModelImpl.getInstance().getMediaDetail(newsId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SimpleObserver<CoinMediaDetailBean>() {
                    @Override
                    public void onNext(CoinMediaDetailBean value) {
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
