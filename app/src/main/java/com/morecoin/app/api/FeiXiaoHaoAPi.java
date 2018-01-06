package com.morecoin.app.api;

import com.morecoin.app.bean.CoinNoticeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by wxy on 2018/1/6.
 */

public interface FeiXiaoHaoAPi {
//    http://api.fxh.io/v1/search/?keyword=nas 搜索
//    http://api.fxh.io/v1/coininfo/nebulas/?sitecode=gate-io&pair=nas_usdt 项目详情
//    http://api.fxh.io/v1/cointicker/nebulas/?sitecode=gate-io&pair=nas_usdt/?page=1 //项目行情列表
//    https://mapi.feixiaohao.com/search/relatedword?q=nas&limit=12 //快速搜索
//    http://api.fxh.io/v1/news/noticelist/?page=1 //公告

    @GET("v1/news/noticelist")
    Observable<CoinNoticeBean> getNotice(@QueryMap HashMap<String, String> map);

}
