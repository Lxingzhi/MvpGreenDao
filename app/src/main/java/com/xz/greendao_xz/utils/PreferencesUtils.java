package com.xz.greendao_xz.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类，方便获取以及添加信息
 * Created by ZhangYi on 2016/2/1.
 */
public class PreferencesUtils {
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    public PreferencesUtils(Context context, String preferenceName){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    /**
     * SharedPreferences中boolean信息的获取
     * @param key 对应的key
     * @param defValue 如果对应的key获取不到信息，返回的默认树枝
     * @return 获取的结果
     */
    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * SharedPreferences中boolean信息的存入
     * @param key 存入信息的key
     * @param value 存入到该key的值
     */
    public void putBoolean(String key, boolean value){
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    /**
     * SharedPreferences中String信息的获取
     * @param key 对应的key
     * @param defValue 如果对应的key获取不到信息，返回的默认值
     * @return 获取的结果
     */
    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }
    /**
     * SharedPreferences中String信息的存入
     * @param key 存入信息的key
     * @param value 存入到该key的值
     */
    public void putString(String key, String value){
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    /**
     * SharedPreferences中Int信息的获取
     * @param key 对应的key
     * @param defValue 如果对应的key获取不到信息，返回的默认值
     * @return 获取的结果
     */
    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }
    /**
     * SharedPreferences中Int信息的存入
     * @param key 存入信息的key
     * @param value 存入到该key的值
     */
    public void putInt(String key, int value) {
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    /**
     * SharedPreferences中Long信息的获取
     * @param key 对应的key
     * @param value 如果对应的key获取不到信息，返回的默认值
     * @return 获取的结果
     */
    public void putLong(String key, long value) {
        editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    /**
     * SharedPreferences中Long信息的存入
     * @param key 存入信息的key
     * @param defValue 存入到该key的值
     */
    public long getLong(String key, Long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }
}
