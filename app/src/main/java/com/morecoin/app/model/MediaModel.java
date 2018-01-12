package com.morecoin.app.model;

import com.morecoin.app.bean.CoinMediaBean;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/2.
 */

public interface MediaModel {
    /**
     * 获得币媒体列表
     */
    Observable<CoinMediaBean> getMediaList();
}
