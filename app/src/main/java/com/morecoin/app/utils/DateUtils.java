package com.morecoin.app.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wxy on 2018/1/3.
 */

public class DateUtils {
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String getDateTimes(long timeMillis) {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(TIME_FORMAT);
        return mDateFormat.format(new Date(timeMillis * 1000));
    }

    public static String getNowTimeStr(){
        long time=System.currentTimeMillis();
        final Calendar mCalendar= Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return mCalendar.get(Calendar.YEAR) + "." + (mCalendar.get(Calendar.MONTH) + 1)
                + "." + mCalendar.get(Calendar.DAY_OF_MONTH)+ " 今天 " +
                "星期" + GetCH(mCalendar.get(Calendar.DAY_OF_WEEK));
    }

    public static String getMessageTime(String timeStr) {
        DateFormat fmt = new SimpleDateFormat(TIME_FORMAT);
        Date date = null;
        try {
            date = fmt.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        return format.format(date);
    }


    private static String GetCH(int input) {
        String sd = "";
        switch (input) {
            case 2:
                sd = "一";
                break;
            case 3:
                sd = "二";
                break;
            case 4:
                sd = "三";
                break;
            case 5:
                sd = "四";
                break;
            case 6:
                sd = "五";
                break;
            case 7:
                sd = "六";
                break;
            case 1:
                sd = "日";
                break;
            default:
                break;
        }
        return sd;
    }

    private static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }
}
