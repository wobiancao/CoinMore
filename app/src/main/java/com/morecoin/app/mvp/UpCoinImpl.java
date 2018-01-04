package com.morecoin.app.mvp;

import android.support.annotation.NonNull;

import com.morecoin.app.base.BasePresenterImpl;
import com.morecoin.app.base.IView;
import com.morecoin.app.base.observer.SimpleObserver;
import com.morecoin.app.bean.CoinUpBean;
import com.morecoin.app.bean.CoinUpEntity;
import com.morecoin.app.bean.InfoBean;
import com.morecoin.app.model.impl.BiUpModelImpl;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wxy on 2018/1/4.
 */

public class UpCoinImpl extends BasePresenterImpl<UpCoinContract.UpCoinIView> implements UpCoinContract.UpCoinPresenter {
    private final long timeTask = 30 * 1000;
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
        BiUpModelImpl.getInstance().getUpList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SimpleObserver<CoinUpBean>() {
                    @Override
                    public void onNext(CoinUpBean value) {
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
