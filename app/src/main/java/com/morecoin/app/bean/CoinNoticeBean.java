package com.morecoin.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wxy on 2018/1/6.
 */

public class CoinNoticeBean implements Serializable {
    public int PageSize;
    public String Status;
    public List<CoinNoticeEntity> NoticeList;
}
