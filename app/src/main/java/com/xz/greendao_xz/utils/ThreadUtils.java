package com.xz.greendao_xz.utils;

import android.os.Handler;

/**
 * 在程序中使用线程的工具
 * Created by ZhangYi on 2016/2/2.
 */
public class ThreadUtils {
    /**
     * 运行在非UI线程中，则将Runnable直接加入到线程池中
     * @param runnable Runnable
     */
    public static void runInThread(Runnable runnable){
        DefaultThreadPool.getInstance().execute(runnable);
    }
    private static Handler handler = new Handler();
    /**
     * 运行在UI线程中，将Runnable交给handler进行处理
     * @param runnable Runnable
     */
    public static void runUIThread(Runnable runnable) {
        handler.post(runnable);
    }

    public static void removeAllThread(){
        DefaultThreadPool.getInstance().removeAllTask();
    }

    public static void shutDownAllThread(){
        DefaultThreadPool.getInstance().shutDownNow();
    }

    public static long getThreadCount(){
       return DefaultThreadPool.getInstance().getTaskCount();
    }
}
