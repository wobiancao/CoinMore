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

public class BiShiJieModelImpl extends BaseModelImpl implements InfoModel {
    public static final String HOST = "http://m.bishijie.com/";
    private static final String PAGE_URL = "home/newsflash/getnextpage?page=0";
    public static BiShiJieModelImpl getInstance() {
        return new BiShiJieModelImpl();
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
                    Element listGroup = doc.getElementsByClass("daynews dateitem").get(0);
                    String mDate = listGroup.getElementsByTag("h2").get(0).text();
                    infoBean.mDate = mDate;
                    Elements listEs = listGroup.getElementsByTag("article");
                    if (null != listEs && listEs.size() > 1) {
                        List<InfoEntity> infoList = new ArrayList<InfoEntity>();
                        for (int i = 0; i < listEs.size(); i++) {
                            InfoEntity infoEntity = new InfoEntity();
                            Element item = listEs.get(i);
                            String mTime = item.getElementsByTag("h3").get(0).text();
                            String mDetail = item.getElementsByClass("text_show").get(0).text();
                            Elements focus = item.getElementsByClass("focus");
                            String mColor = focus != null && focus.size() > 0 ? "#ED6979" : "#666666";
                            infoEntity.mDetail = mDetail;
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
