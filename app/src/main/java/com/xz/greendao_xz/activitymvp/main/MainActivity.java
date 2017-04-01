package com.xz.greendao_xz.activitymvp.main;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.xz.greendao_xz.R;
import com.xz.greendao_xz.activitymvp.main.presenter.MainActivityPresenter;
import com.xz.greendao_xz.activitymvp.main.view.IMainActivity;
import com.xz.greendao_xz.databasebean.User;
import com.xz.greendao_xz.slideview.SwipeMenuListView;
import com.xz.greendao_xz.utils.Util;
import com.xz.greendao_xz.view.UpdateUseDialog;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainActivity {

    private Button btnAdd; //增改
    private SwipeMenuListView listView;
    private MainActivityPresenter mainActivityPresenter;
    private EditText edName, edAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }



    private void initView() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        listView = (SwipeMenuListView) findViewById(R.id.id_swipemenulistView);
        mainActivityPresenter.initListView(listView);
        edName = (EditText) findViewById(R.id.ed_name);
        edAge = (EditText) findViewById(R.id.ed_age);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:  //初始化数据 往里加默认数据
                if (!TextUtils.isEmpty(edName.getText()) &&!TextUtils.isEmpty(edAge.getText())) {
                    mainActivityPresenter.saveUser(edName.getText().toString(), edAge.getText().toString());
                }else {
                    Util.toast(this,"姓名或者年龄未填",500);
                }
                break;
        }
    }
    private void initData() {
        mainActivityPresenter = new MainActivityPresenter(this);
    }


    @Override
    public void initEditText() {
        edName.setText("");
        edAge.setText("");
    }
}
