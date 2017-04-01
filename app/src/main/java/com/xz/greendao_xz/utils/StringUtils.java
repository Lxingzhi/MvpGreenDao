package com.xz.greendao_xz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 存在时间操作 ，需要优化，
 */
public class StringUtils {

    /**
     * @param @param  time 字符串时间(yyyy年MM月dd日)
     * @param @return
     * @param @throws ParseException 解析异常
     * @return Date 返回类型
     * @Title: stringToDate
     * @Description: TODO
     */
    public static Date stringToDate(String time) throws ParseException {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        date = formatter.parse(time);
        return date;
    }

    /**
     *  传入字符串 获得long类型
     * @param time
     * @return
     * @throws ParseException
     */
    public static long stringToLongStringDate(String time)
            throws ParseException {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        date = formatter.parse(time);
        return date.getTime();
    }

    /**
     * 获取当前时间并转换成long
     *
     * @return
     * @throws ParseException
     */
    public static long stringToLongStringDateCurrent() throws ParseException {
        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy年MM月dd日");
        String retStrFormatNowDate = sdFormatter.format(nowDate);
        Date date = null;
        date = sdFormatter.parse(retStrFormatNowDate);
        return date.getTime();
    }

    public static long stringToLongStringDateCurrent(String timeStr) throws ParseException {

        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date nowDate =sdFormatter.parse(timeStr);
        String retStrFormatNowDate = sdFormatter.format(nowDate);
        Date date = null;
        date = sdFormatter.parse(retStrFormatNowDate);
        return date.getTime();
    }
    public static String getInsertDate() {
        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy年MM月dd日");
        String retStrFormatNowDate = sdFormatter.format(nowDate);
        return retStrFormatNowDate;
    }


    /**
     *
     * @param inStr 传过来的字符串
     * @param strSize 字符串长度
     * @param startStr  字符串开头含有多少位
     * @return outStr 返回的字符串
     */
    public static String MakeOutString(String inStr, int strSize, int startStr) {
        StringBuilder outStr;
        if (inStr.length() > strSize) {
            outStr = new StringBuilder();
            //1.添加 前面的字符
            outStr.append(inStr.substring(0, startStr));
            //2.添加省略
            outStr.append("...");
            //3.添加后面的字符
            outStr.append(inStr.substring(inStr.length() - startStr, inStr.length()));
            return outStr.toString();
        } else {
            return inStr;
        }

    }


}
