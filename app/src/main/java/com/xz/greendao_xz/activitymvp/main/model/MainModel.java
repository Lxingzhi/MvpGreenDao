package com.xz.greendao_xz.activitymvp.main.model;

import com.xz.greendao_xz.databasebean.User;
import com.xz.greendao_xz.utils.DbUtils;

import java.util.List;

/**
 * author: lys
 * date: 2017/2/27 10:46
 * email: njzy_lys@163.com
 */

public class MainModel implements  IMainModel{

    @Override
    public void saveUser(User user) {
        DbUtils.getUserService().saveOrUpdate(user);
    }

    @Override
    public void deleteUser(User user) {
        DbUtils.getUserService().delete(user);
    }

    @Override
    public void updateUser(User user) {
        DbUtils.getUserService().update(user);
    }

    @Override
    public List<User> queryUserAll() {
        return  DbUtils.getUserService().queryAll();
    }
}
