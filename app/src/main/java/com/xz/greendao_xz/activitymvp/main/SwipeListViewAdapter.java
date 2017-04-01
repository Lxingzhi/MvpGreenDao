package com.xz.greendao_xz.activitymvp.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xz.greendao_xz.R;
import com.xz.greendao_xz.databasebean.User;
import com.xz.greendao_xz.icommon.ISwipeListViewAdapter;
import com.xz.greendao_xz.slideview.BaseSwipListAdapter;
import com.xz.greendao_xz.utils.*;

import java.util.List;

/**
 * author: lys
 * date: 2017/2/27 11:06
 * email: njzy_lys@163.com
 */

public class SwipeListViewAdapter extends BaseSwipListAdapter implements ISwipeListViewAdapter {
    private List<User> userList;
    private Context context;
    private TextView showName, showAge;

    public SwipeListViewAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int positon) {
        return userList.get(positon);
    }

    @Override
    public long getItemId(int positon) {
        return positon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        }
        initView(convertView);
        if (position <= userList.size() - 1) {
            initData(convertView, position);
        } else {
            initEmpty();
        }
        return convertView;
    }

    public void initEmpty() {
        showName.setText("");
        showAge.setText("");
    }

    @Override
    public void initView(View view) {
        showName = SwipeListViewViewHolder.get(view, R.id.id_show_name);
        showAge = SwipeListViewViewHolder.get(view, R.id.id_show_age);
    }

    @Override
    public void initData(View view, int position) {
        showName.setText(""+context.getResources().getString(R.string.name) + userList.get(position).getName());
        showAge.setText(""+context.getResources().getString(R.string.age) + userList.get(position).getAge());
    }

    //解决空白处侧滑删除的出现。
    @Override
    public boolean getSwipEnableByPosition(int position) {
        // TODO Auto-generated method stub
        if (position > userList.size() - 1) {
            return false;
        }
        return true;
    }
}
