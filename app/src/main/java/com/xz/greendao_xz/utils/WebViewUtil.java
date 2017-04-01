package com.xz.greendao_xz.utils;

import android.webkit.WebView;


/**
 * Created by zhangbeibei on 16/11/15.
 * email:hearstzhang@gmail.com
 * 功能描述：WebView 加载内容工具类
 */


public class WebViewUtil {

    /**
     * 传进来的文件路径带jiemihou，首先去掉
     *
     * @param webView
     * @param filePath
     * @return
     */
    public static boolean openHtm(WebView webView, String filePath) {
        if (filePath == null) return false;
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        String filePath1 = "file:///mnt/sdcard" + "/datas/manual/" + fileName;
//        if (webView == null)
//            return false;
//        if (filePath1 == null)
//            return false;
//        File file = new File(filePath1);
//        if (!file.exists()) {
//            return false;
//        }
        webView.loadUrl(filePath1);
        return true;
    }
}
