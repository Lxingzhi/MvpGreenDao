package com.xz.greendao_xz.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * @author ghy
 * @ClassName: AppUtils
 * @Description: 此类主要存储无法形成类别的数据，但又需要保存和操作的
 * @date 2016-6-17 下午3:34:31
 */
public class AppUtils {
    public static void hideInput(Activity context) {
        context.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public static int dp2px(Activity context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;    //得到宽度
    }


    public static int getScreenHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;    //得到宽度
    }
}
