package com.morecoin.app.utils;

/**
 * Created by wxy on 2018/1/4.
 */

public class ApiUseUtil {
    public static String getDecodeStr(String timeTemp){
       return MD5Util.getStringMD5(timeTemp + "9527" + timeTemp.substring(0, 6));
    }
}
