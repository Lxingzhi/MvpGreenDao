package com.xz.greendao_xz.greendao.service;

import com.xz.greendao_xz.databasebean.User;

import org.greenrobot.greendao.AbstractDao;

/**
 * author: ZhangYi
 * date: 2017/2/17 09:36
 * email: 307221280@qq.com
 */

public class UserService  extends BaseService<User, Integer>{

    public UserService(AbstractDao dao) {
        super(dao);
    }
}
