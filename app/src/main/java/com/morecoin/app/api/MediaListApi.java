package com.morecoin.app.api;

import com.morecoin.app.bean.CoinMediaBean;
import com.morecoin.app.bean.CoinMediaDetailBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by wxy on 2018/1/2.
 */

public interface MediaListApi {
    @GET("news/m_news")
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:Keep-Alive",
            "Cache-Control:no-cache"})
    Observable<CoinMediaBean> getMediaList(@QueryMap HashMap<String, String> map);

    @GET("news/m_topic/{newsId}")
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:Keep-Alive",
            "Cache-Control:no-cache"})
    Observable<CoinMediaDetailBean> getMediaDetail(@Path("newsId") String newsId);
}
