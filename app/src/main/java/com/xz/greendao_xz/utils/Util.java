package com.xz.greendao_xz.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by zhaihao on 2016/10/22.
 */

public class Util {

    /**
     * 防止toast排队显示
     */

    private static Toast toast;

    /**
     * 直接传字符串
     *
     * @param context
     * @param msg
     * @param duration
     */
    public static void toast(Context context, String msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
        }
        toast.show();

    }

    /**
     * 传Resource中 String的ID 如 R.string.app_name
     *
     * @param context
     * @param id
     * @param duration
     */
    public static void toast(Context context, @StringRes int id, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, context.getString(id), duration);
        } else {
            toast.setText(context.getString(id));
        }
        toast.show();
    }


    /**
     * 判断字符串是否为空
     *
     * @param msg
     * @return true 空字符串
     */
    public static boolean isEmptyString(String msg) {
        return msg == null || msg.equals("");
    }


    /**
     * 避免按钮被重复点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    public static String formatTime(long milliseconds) {
        String hour = String.valueOf(milliseconds / 3600000);
        String minute = String.valueOf((milliseconds % 3600000) / 60000);
        String second = String.valueOf(((milliseconds % 3600000) % 60000) / 1000);
        hour = deal(hour);
        minute = deal(minute);
        second = deal(second);
        return hour + ":" + minute + ":" + second;
    }

    private static String deal(String time) {
        if (time.length() == 1) {
            if (time.equals("0"))
                time = "00";
            else
                time = "0" + time;
        }
        return time;
    }
}
