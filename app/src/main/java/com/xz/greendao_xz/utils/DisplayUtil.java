package com.xz.greendao_xz.utils;

import android.content.Context;

/**
 * Created by ZhangYi on 2016/5/25.
 */
public class DisplayUtil {
    /**
     * 将px值转换为dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return 转换后的dp值
     */
    public static int px2dp(Context context, int pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale + 0.5f);
    }

    /**
     * 将dp值转换为px值
     * @param context
     * @param dipValue
     * @return
     */
    public static int dp2px(Context context, int dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale + 0.5f);
    }

    /**
     * 将px值转化为sp值，保证文字大小不变
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, int pxValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int ) (pxValue/fontScale + 0.5f);
    }

    /**
     * 将sp值转化为px值
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, int spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenWidth(Context context){
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        return widthPixels;
    }
}