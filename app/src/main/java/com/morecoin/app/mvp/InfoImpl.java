package com.morecoin.app.mvp;

import android.support.annotation.NonNull;

import com.morecoin.app.base.BasePresenterImpl;
import com.morecoin.app.base.IView;
import com.morecoin.app.base.observer.SimpleObserver;
import com.morecoin.app.bean.InfoBean;
import com.morecoin.app.model.impl.BiCaijingModelImpl;
import com.morecoin.app.model.impl.BiKnowModelImpl;
import com.morecoin.app.model.impl.BiShiJieModelImpl;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wxy on 2017/12/28.
 */

public class InfoImpl extends BasePresenterImpl<InfoContract.InfoIView> implements InfoContract.InfoPresenter {
    @Override
    public void attachView(@NonNull IView iView) {
        super.attachView(iView);

    }

    @Override
    public void detachView() {

    }

    @Override
    public void onParseInfo(String mHost) {
        Observable<InfoBean> beanObservable = null;
        if (mHost.equals(BiShiJieModelImpl.HOST)) {
            beanObservable = BiShiJieModelImpl.getInstance().getInfoList();
        } else if (mHost.equals(BiCaijingModelImpl.HOST)) {
            beanObservable =  BiCaijingModelImpl.getInstance().getInfoList();
        }else if (mHost.equals(BiKnowModelImpl.HOST)){
            beanObservable = BiKnowModelImpl.getInstance().getInfoList();
        }
        if (beanObservable != null){
            beanObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new SimpleObserver<InfoBean>() {
                        @Override
                        public void onNext(InfoBean value) {
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
}
