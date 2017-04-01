package com.xz.greendao_xz.activitymvp.main.presenter;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.xz.greendao_xz.R;
import com.xz.greendao_xz.activitymvp.main.MainActivity;
import com.xz.greendao_xz.activitymvp.main.SwipeListViewAdapter;
import com.xz.greendao_xz.activitymvp.main.model.IMainModel;
import com.xz.greendao_xz.activitymvp.main.model.MainModel;
import com.xz.greendao_xz.activitymvp.main.view.IMainActivity;
import com.xz.greendao_xz.databasebean.User;
import com.xz.greendao_xz.slideview.SwipeMenu;
import com.xz.greendao_xz.slideview.SwipeMenuCreator;
import com.xz.greendao_xz.slideview.SwipeMenuItem;
import com.xz.greendao_xz.slideview.SwipeMenuListView;
import com.xz.greendao_xz.utils.AppUtils;
import com.xz.greendao_xz.utils.Util;
import com.xz.greendao_xz.view.UpdateUseDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lys
 * date: 2017/2/27 10:18
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivity mainActivity;
    private IMainModel mainModel;
    private MainActivity activity;
    private SwipeListViewAdapter swipeListViewAdapter;
    private List<User> allUserList;
    private UpdateUseDialog updateUseDialog;
    SwipeMenuCreator creator;

    public MainActivityPresenter(IMainActivity mainActivity) {
        this.mainActivity = mainActivity;
        activity = (MainActivity) mainActivity;
        mainModel = new MainModel();
        queryUserAll();
        initUpdateUseDialog();
    }


    @Override
    public void saveUser(String name, String age) {
        User user = new User();
        user.setName(name);
        user.setAge(Integer.valueOf(age));
        mainModel.saveUser(user);
        allUserList.add(user);
        swipeListViewAdapter.setUserList(allUserList);
        activity.initEditText();
        swipeListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteUser(User user) {
        allUserList.remove(user);
        mainModel.deleteUser(user);
        swipeListViewAdapter.setUserList(allUserList);
        swipeListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateUser(User user) {
        mainModel.updateUser(user);
        queryUserAll();
        swipeListViewAdapter.setUserList(allUserList);
        swipeListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public List<User> queryUserAll() {
        if (allUserList != null) {
            allUserList.clear();
            allUserList = null;
        }
        allUserList = mainModel.queryUserAll();
        return allUserList;
    }

    @Override
    public void initListView(SwipeMenuListView swipeMenuListView) {
        initListviewConfig(swipeMenuListView);
        initListViewLisener(swipeMenuListView);
    }

    private void initListViewLisener(SwipeMenuListView swipeMenuListView) {
        //
        swipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateUseDialog.setUser(allUserList.get(i));
                updateUseDialog.show();
            }
        });
        initCreater();
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                deleteUser(allUserList.get(position));
                return false;
            }
        });
    }

    private void initUpdateUseDialog() {
        if (updateUseDialog == null) {
            updateUseDialog = new UpdateUseDialog(activity);
        }
        updateUseDialog.setOnBtnClickListener(new UpdateUseDialog.OnBtnClickListener() {
            @Override
            public void onOkclick(User user) {
                updateUser(user);
            }
        });
    }

    private void initListviewConfig(SwipeMenuListView swipeMenuListView) {
        if (allUserList == null) {
            swipeListViewAdapter = new SwipeListViewAdapter(new ArrayList<User>(), activity);
        } else {
            swipeListViewAdapter = new SwipeListViewAdapter(allUserList, activity);
        }

        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setAdapter(swipeListViewAdapter);
    }

    //设置删除图标大小
    public void initCreater() {
        if (creator == null) {
            creator = new SwipeMenuCreator() {

                @Override
                public void create(SwipeMenu menu) {
                    // create "delete" item
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            activity.getApplicationContext());
                    // set item background
                    deleteItem.setBackground(R.color.blue);
                    // set item width
                    deleteItem.setWidth(AppUtils.dp2px(activity, 48));
                    deleteItem.setHeight(AppUtils.dp2px(activity, 48));
                    // set a icon
                    deleteItem.setIcon(R.mipmap.ic_delete);
                    // add to menu
                    menu.addMenuItem(deleteItem);
                }
            };
        }
    }
}
