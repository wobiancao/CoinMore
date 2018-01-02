package com.morecoin.app.model;

import com.morecoin.app.bean.InfoBean;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/2.
 */

public interface InfoModel {
    /**
     * 获得讯息列表
     */
    Observable<InfoBean> getInfoList();
}
