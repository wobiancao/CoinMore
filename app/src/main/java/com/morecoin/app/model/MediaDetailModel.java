package com.morecoin.app.model;

import com.morecoin.app.bean.CoinMediaDetailBean;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/12.
 */

public interface MediaDetailModel {
    /**
     * 获得币媒体详情
     */
    Observable<CoinMediaDetailBean> getMediaDetail(String newsId);
}
