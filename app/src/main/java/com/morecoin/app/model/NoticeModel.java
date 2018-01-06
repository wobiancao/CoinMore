package com.morecoin.app.model;

import com.morecoin.app.bean.CoinNoticeBean;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/6.
 */

public interface NoticeModel {
    Observable<CoinNoticeBean> getNotice();
}
