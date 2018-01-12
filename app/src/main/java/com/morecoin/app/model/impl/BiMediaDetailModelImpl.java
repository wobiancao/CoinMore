package com.morecoin.app.model.impl;

import com.morecoin.app.api.MediaListApi;
import com.morecoin.app.base.BaseModelImpl;
import com.morecoin.app.bean.CoinMediaDetailBean;
import com.morecoin.app.model.MediaDetailModel;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/12.
 */

public class BiMediaDetailModelImpl extends BaseModelImpl implements MediaDetailModel {
    public static final String HOST = "https://x.btckan.com/";
    public static BiMediaDetailModelImpl getInstance() {
        return new BiMediaDetailModelImpl();
    }


    @Override
    public Observable<CoinMediaDetailBean> getMediaDetail(String newsId) {
        return getREtrofitJson(HOST).create(MediaListApi.class).getMediaDetail(newsId);
    }
}
