package com.morecoin.app.bean;

import java.io.Serializable;

/**
 * Created by wxy on 2018/1/4.
 */

public class CoinUpBean implements Serializable {
    public int code;// ==0才对
    public String message;
    public long timestamp;
    public CoinUpDataEntity data;
}
