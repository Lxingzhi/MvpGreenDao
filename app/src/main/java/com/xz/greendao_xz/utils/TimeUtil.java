package com.xz.greendao_xz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangbeibei on 16/11/17.
 * email:hearstzhang@gmail.com
 * 功能描述：根据需要对时间进行处理
 */

public class TimeUtil {
    //获取用户添加记录距离现在的时间，刚刚、<10min、<1h、<1day、<3day、>3day
    public static String getMyTimeDescription(Long sysTimeInMills) {
        String result = "刚刚";
        Long tempTime = System.currentTimeMillis();
        Long gap = tempTime - sysTimeInMills;
        if (gap < 0) {
            result = "刚刚";
            return result;
        }

        if (gap > 3 * getMillSecondsOfOneDay()) {
            result = ">3天";
            return result;
        }
        if (gap > 1 * getMillSecondsOfOneDay()) {
            result = "<3天";
            return result;
        }
        if (gap > 60 * 60 * 1000l) {
            result = "<1天";
            return result;
        }
        if (gap > 10 * 60 * 1000l) {
            result = "<1h";
            return result;
        }
        if (gap > 1 * 60 * 1000l) {
            result = "<10分钟";
            return result;
        }
        return result;
    }

    public static Long getMillSecondsOfOneDay() {
        return 1000 * 24 * 60 * 60l;
    }

    // Date to 19901001
    public static int changeDate2IntDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String strDate = format.format(date);
        return Integer.parseInt(strDate);
    }

    //将毫秒数转换为19901001格式
    public static int changeMillSecond2IntDate(Long millSeconds) {
        Date date = new Date(millSeconds);
        return changeDate2IntDate(date);
    }

    public static int getIntDate(int position) {
        int result = 0;
        long gapMillSeconds = 0;
        Long tempMillSeconds = System.currentTimeMillis();
        switch (position) {
            case 0:
                result = 19700101;
                break;
            case 1://7天
                gapMillSeconds = 7 * getMillSecondsOfOneDay();
                result = changeMillSecond2IntDate(tempMillSeconds - gapMillSeconds);
                break;
            case 2://30天
                gapMillSeconds = 30 * getMillSecondsOfOneDay();
                result = changeMillSecond2IntDate(tempMillSeconds - gapMillSeconds);
                break;
            case 3://90天
                gapMillSeconds = 90 * getMillSecondsOfOneDay();
                result = changeMillSecond2IntDate(tempMillSeconds - gapMillSeconds);
                break;
        }
        return result;
    }

    /**
     * //20161121
     */
    public static int TYPE_INT_0 = 0;
    public static int TYPE_INT_1 = 1;//2016-11-21
    public static int TYPE_INT_2 = 2;//2016/11/21

    // Date to 19901001
    private static String changeDate2IntStrDate(Date date, String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy" + str + "MM" + str + "dd");
        String strDate = format.format(date);
        return strDate;
    }

    public static String getIntDateByType(Long millSeconds, int type) {
        Date date = new Date(millSeconds);
        String result = null;
        switch (type) {
            case 0:
                result = changeDate2IntStrDate(date, "");
                break;
            case 1:
                result = changeDate2IntStrDate(date, "-");
                break;
            case 2:
                result = changeDate2IntStrDate(date, "/");
                break;
        }
        return result;
    }

    // 20161117 2016-11-17
    public static String getFormatDate(int timeInt) {
        String time = timeInt + "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(time.substring(0, 4) + "-");
        stringBuffer.append(time.substring(4, 6) + "-");
        stringBuffer.append(time.substring(6, 8));
        return stringBuffer.toString();
    }
}
