package com.morecoin.app.model.impl;

import com.morecoin.app.api.MediaListApi;
import com.morecoin.app.base.BaseModelImpl;
import com.morecoin.app.bean.CoinMediaBean;
import com.morecoin.app.model.MediaModel;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by wxy on 2018/1/12.
 */

public class BiMediaModelImpl extends BaseModelImpl implements MediaModel {
    public static final String HOST = "https://x.btckan.com/";
    public static BiMediaModelImpl getInstance() {
        return new BiMediaModelImpl();
    }
    @Override
    public Observable<CoinMediaBean> getMediaList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("category", "11");
        hashMap.put("beforeID", "0");
        hashMap.put("pageSize", "20");
        return getREtrofitJson(HOST).create(MediaListApi.class).getMediaList(hashMap);
    }
}
