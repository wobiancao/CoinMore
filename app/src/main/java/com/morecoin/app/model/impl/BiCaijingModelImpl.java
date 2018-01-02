package com.morecoin.app.model.impl;

import com.morecoin.app.api.InfoApi;
import com.morecoin.app.base.BaseModelImpl;
import com.morecoin.app.bean.InfoBean;
import com.morecoin.app.bean.InfoEntity;
import com.morecoin.app.model.InfoModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Created by wxy on 2018/1/2.
 */

public class BiCaijingModelImpl extends BaseModelImpl implements InfoModel {
    public static final String HOST = "http://www.jinse.com/";
    private static final String PAGE_URL = "/lives";
    public static BiCaijingModelImpl getInstance() {
        return new BiCaijingModelImpl();
    }

    @Override
    public Observable<InfoBean> getInfoList() {
        return getRetrofitObject(HOST).create(InfoApi.class).getInfoPage(PAGE_URL).flatMap(new Function<String, Observable<InfoBean>>() {
            @Override
            public Observable<InfoBean> apply(String s) throws Exception {
                return analyPageInfo(s);
            }
        });
    }

    private Observable<InfoBean> analyPageInfo(final String content) {
        return Observable.create(new ObservableOnSubscribe<InfoBean>() {
            @Override
            public void subscribe(ObservableEmitter<InfoBean> e) throws Exception {
                InfoBean infoBean = new InfoBean();
                try {
                    Document doc = Jsoup.parse(content);
                    Element listGroup = doc.getElementsByClass("live news-flash").get(0);
                    String mDate = listGroup.getElementsByClass("con-item clearfix lost-area").get(0).getElementsByTag("span").get(1).text();
                    infoBean.mDate = mDate;
                    Elements listEs = listGroup.getElementsByClass("lost").get(0).getElementsByTag("li");
                    if (null != listEs && listEs.size() > 1) {
                        List<InfoEntity> infoList = new ArrayList<InfoEntity>();
                        for (int i = 0; i < listEs.size(); i++) {
                            InfoEntity infoEntity = new InfoEntity();
                            Element item = listEs.get(i);
                            String mTime = item.getElementsByClass("live-time").get(0).text();
                            String mDetail = item.getElementsByClass("live-info").get(0).text();
                            Elements focus = item.getElementsByClass("clearfix  red ");
                            String mColor = focus != null && focus.size() > 0 ? "#ED6979" : "#666666";
                            infoEntity.mDetail = mDetail.replace("[查看原文]", "");
                            infoEntity.mTextColor = mColor;
                            infoEntity.mTime = mTime;
                            infoList.add(infoEntity);
                        }
                        infoBean.mData = infoList;
                        e.onNext(infoBean);
                    } else {
                        e.onNext(infoBean);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    e.onNext(infoBean);
                }
                e.onComplete();
            }
        });
    }
}
