package com.xz.greendao_xz.utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangbeibei on 16/11/2.
 * email:hearstzhang@gmail.com
 * 功能描述：文件操作工具类，使用RxJava来实现
 * 复制文件到指定位置 复制文件夹到指定位置
 */
public class RxJavaToFileUtil {
    /**
     * 删除指定路径下的文件
     *
     * @param filePath
     * @return
     */
    public static Observable deleteFile(final String filePath) {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    if (file.delete()) {
                        subscriber.onNext(filePath + "文件删除成功");
                        return;
                    }
                }
                Throwable throwable = new Throwable("文件删除失败");
                subscriber.onError(throwable);
            }
        });
        return observable;
    }
    /**
     *
     */

    /**
     * 删除指定路径的文件夹
     *
     * @param folderPath
     * @return
     */
    public static Observable deleteFolder(String folderPath) {
        Observable observable = null;
        return observable;
    }


    //文件是否存在
    public static boolean exists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * String srcFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/kmPlugins.zip";
     * String desFolderPath = Environment.getExternalStorageDirectory().getPath() + "/datas";
     * //将单个文件拷贝到指定文件夹
     *
     * @param srcFilePath
     * @param desFolderPath
     * @return
     */
    public static Observable copyFile(final String srcFilePath, final String desFolderPath) {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                copyFile(subscriber, srcFilePath, desFolderPath);
            }
        });
        return observable;
    }

    //文件复制个数
    private static int count = 0;

    /**
     * 拷贝文件夹
     * String srcFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download";
     * String desFolderPath = Environment.getExternalStorageDirectory().getPath() + "/datas";
     */
    public static Observable copyFolder(final String srcFolderPath, final String desFolderPath) {

        count=0;
        final List<String> srcFilePaths = new ArrayList<>();
        final List<String> desFolderPaths = new ArrayList<>();

        //获取需要复制的文件及目标文件夹列表
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                getAllFilePaths(srcFolderPath, desFolderPath, srcFilePaths, desFolderPaths);
                subscriber.onNext("准备复制文件共：" + srcFilePaths.size() + "个");

                for (int i = 0; i < srcFilePaths.size(); i++) {
                    final int position = i;
                    copyFile(srcFilePaths.get(i), desFolderPaths.get(i)).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                            .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                            .subscribe(new Observer<String>() {
                                @Override
                                public void onNext(String info) {
                                    Log.v("1234567890", info);
                                }

                                @Override
                                public void onCompleted() {
                                    count++;
                                    Log.v("1234567890", "onCompleted executed!");
                                    if (count == srcFilePaths.size()) {
                                        subscriber.onCompleted();
                                        Log.v("1234567890", "文件夹复制完成!");
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.v("1234567890", e.getMessage());
                                }
                            });
                }
            }
        });
        return observable;
    }


    private static void copyFile(Subscriber subscriber, String srcFilePath, String desFolderPath) {
        File srcFile = new File(srcFilePath);
        if (!srcFile.exists()) {
            Throwable throwable = new Throwable(srcFilePath + "文件不存在");
            subscriber.onError(throwable);
            return;
        }
        //file exist
        try {
            int byteSum = 0;
            int byteRead = 0;
            InputStream inStream = new FileInputStream(srcFilePath); // 读入原文件
            new File(desFolderPath).mkdirs();
            FileOutputStream fs = new FileOutputStream(desFolderPath
                    + File.separator + srcFile.getName());
            byte[] buffer = new byte[1444];
            while ((byteRead = inStream.read(buffer)) != -1) {
                byteSum += byteRead; // 字节数 文件大小
                System.out.println(byteSum);
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            subscriber.onNext(srcFilePath + "文件复制完成");
            subscriber.onCompleted();
        } catch (Exception e) {
            Throwable throwable = new Throwable(srcFilePath + "赋值文件出错");
            subscriber.onError(throwable);
            e.printStackTrace();
        }
    }

    private static void getAllFilePaths(String srcFolderPath, final String desFolderPath, List<String> srcFilePaths, List<String> desFolderPaths) {
        String tempOldPath = srcFolderPath + File.separator;
        try {
            File a = new File(srcFolderPath);
            (new File(desFolderPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            String[] file = a.list();// 获取文件列表，包括文件名或者子文件夹
            File temp = null;
            for (int i = 0; i < file.length; i++) {// 循环遍历
                // 如果有文件"/" 则不加，没有则加上
                if (srcFolderPath.endsWith(File.separator)) {
                    temp = new File(srcFolderPath + file[i]);
                } else {
                    temp = new File(srcFolderPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    srcFilePaths.add(srcFolderPath + "/" + file[i]);
                    desFolderPaths.add(desFolderPath);
                } else if (temp.isDirectory()) {// 如果是子文件夹，循环调用
                    getAllFilePaths(srcFolderPath + "/" + file[i], desFolderPath + "/" + file[i], srcFilePaths, desFolderPaths);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
