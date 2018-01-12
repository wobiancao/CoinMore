package com.morecoin.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wxy on 2018/1/12.
 */

public class CoinMediaBean implements Serializable {
    public int code;
    public CoinMediaData data;

    public class CoinMediaData implements Serializable{
        public List<CoinMediaEntity> newsList;
    }
}
