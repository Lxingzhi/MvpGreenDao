package com.xz.greendao_xz.activitymvp.main.presenter;

import android.widget.ListView;

import com.xz.greendao_xz.databasebean.User;
import com.xz.greendao_xz.slideview.SwipeMenuListView;

import java.util.List;

/**
 * author: lys
 * date: 2017/2/27 10:34
 * email: njzy_lys@163.com
 */

public interface IMainActivityPresenter {
    void saveUser(String name,String age);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> queryUserAll();
    void initListView(SwipeMenuListView swipeMenuListView);
}
