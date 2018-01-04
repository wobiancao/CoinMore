package com.morecoin.app.model.impl;

import com.morecoin.app.api.UpListApi;
import com.morecoin.app.base.BaseModelImpl;
import com.morecoin.app.bean.CoinUpBean;
import com.morecoin.app.model.UpModel;
import com.morecoin.app.utils.ApiUseUtil;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/2.
 */

public class BiUpModelImpl extends BaseModelImpl implements UpModel {
    public static final String HOST = "http://api.lb.mytoken.org/";
    public static BiUpModelImpl getInstance() {
        return new BiUpModelImpl();
    }

    @Override
    public Observable<CoinUpBean> getUpList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "1");
        hashMap.put("keyword", "");
        hashMap.put("tag", "new_currency");
        hashMap.put("page", "1");
        String timestamp = System.currentTimeMillis() + "";
        hashMap.put("timestamp", timestamp);
        hashMap.put("code", ApiUseUtil.getDecodeStr(timestamp));
        hashMap.put("platform", "m");
        return getREtrofitJson(HOST).create(UpListApi.class).getMediaList(hashMap);
    }

}
