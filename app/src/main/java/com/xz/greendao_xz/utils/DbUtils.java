package com.xz.greendao_xz.utils;

import com.xz.greendao_xz.greendao.UserDao;
import com.xz.greendao_xz.greendao.service.UserService;

/**
 * author: lys
 * date: 2017/2/17 09:40
 * email:
 */

public class DbUtils {
    private static UserService userService;

    private static UserDao getUserDao() {
        return DbHelper.getDaoSession().getUserDao();
    }

    public static UserService getUserService() {
        if (userService == null) {//
            userService = new UserService(getUserDao());
        }
        return userService;
    }
}
