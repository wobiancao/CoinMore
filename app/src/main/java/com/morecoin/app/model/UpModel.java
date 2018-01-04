package com.morecoin.app.model;

import com.morecoin.app.bean.CoinUpBean;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/2.
 */

public interface UpModel {
    /**
     * 获得讯息列表
     */
    Observable<CoinUpBean> getUpList();

}
