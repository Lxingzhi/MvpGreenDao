package com.xz.greendao_xz.application;

import android.app.Application;

import com.xz.greendao_xz.utils.DbHelper;

/**
 * author: ZhangYi
 * date: 2017/2/16 16:55
 * email: 307221280@qq.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.init(this);
    }
}
