package com.morecoin.app.model.impl;

import com.morecoin.app.api.FeiXiaoHaoAPi;
import com.morecoin.app.base.BaseModelImpl;
import com.morecoin.app.bean.CoinNoticeBean;
import com.morecoin.app.model.NoticeModel;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/6.
 */

public class BiNoticeModelImpl extends BaseModelImpl implements NoticeModel {
    public static final String HOST = "http://api.fxh.io/";
    public static BiNoticeModelImpl getInstance() {
        return new BiNoticeModelImpl();
    }

    @Override
    public Observable<CoinNoticeBean> getNotice() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", "1");
        return getREtrofitJson(HOST).create(FeiXiaoHaoAPi.class).getNotice(hashMap);
    }
}
