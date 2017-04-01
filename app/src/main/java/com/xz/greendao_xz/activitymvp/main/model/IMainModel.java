package com.xz.greendao_xz.activitymvp.main.model;

import com.xz.greendao_xz.databasebean.User;

import java.util.List;

/**
 * author: lys
 * date: 2017/2/27 10:46
 * email: njzy_lys@163.com
 */

public interface IMainModel {
    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> queryUserAll();
}
