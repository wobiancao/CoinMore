package com.morecoin.app.mvp;

import android.support.annotation.NonNull;

import com.morecoin.app.base.BasePresenterImpl;
import com.morecoin.app.base.IView;
import com.morecoin.app.base.observer.SimpleObserver;
import com.morecoin.app.bean.CoinNoticeBean;
import com.morecoin.app.model.impl.BiNoticeModelImpl;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wxy on 2018/1/4.
 */

public class NoticeCoinImpl extends BasePresenterImpl<NoticeCoinContract.NoticeCoinIView> implements NoticeCoinContract.NoticeCoinPresenter {
    private final long timeTask = 60 * 1000;
    private boolean isFirst = true;
    private Timer timer;

    @Override
    public void attachView(@NonNull IView iView) {
        super.attachView(iView);
        timer = new Timer(true);
    }

    @Override
    public void detachView() {
        if (timer != null) {
            task.cancel();
            timer.cancel();
        }
    }

    TimerTask task = new TimerTask() {
        public void run() {
            //每次需要执行的代码放到这里面。
            onRefresh();
        }
    };

    @Override
    public void onRefresh() {
        BiNoticeModelImpl.getInstance().getNotice()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SimpleObserver<CoinNoticeBean>() {
                    @Override
                    public void onNext(CoinNoticeBean value) {
                        if (mView != null) {
                            mView.onRefreshOver();
                            mView.onBindData(value);
                            if (isFirst) {
                                isFirst = false;
                                if (timer != null) {
                                    timer.schedule(task, new Date(), timeTask);
                                }
                            }
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
