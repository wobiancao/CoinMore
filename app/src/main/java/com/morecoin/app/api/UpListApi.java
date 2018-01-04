package com.morecoin.app.api;

import com.morecoin.app.bean.CoinUpBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by wxy on 2018/1/2.
 */

public interface UpListApi {
    @GET("media/medialist")
    @Headers({"Accept:text/html,application/xhtml+xml,application/xml",
            "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3",
            "Accept-Charset:UTF-8",
            "Keep-Alive:300",
            "Connection:Keep-Alive",
            "Cache-Control:no-cache"})
    Observable<CoinUpBean> getMediaList(@QueryMap HashMap<String, String> map);
}
