package com.xz.greendao_xz.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xz.greendao_xz.R;
import com.xz.greendao_xz.databasebean.User;
import com.xz.greendao_xz.utils.Util;

/**
 * Created by ZhangYi on 2016/5/28.
 */
public class UpdateUseDialog extends Dialog {

    private EditText edName,edAge;
    private Button btnOk,btnCancel;
    private String title;
    private User user;
    private User updateUser;
    private OnBtnClickListener onBtnClickListener;
    public interface OnBtnClickListener {
       void onOkclick(User user);
    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }

    public UpdateUseDialog(Context context) {
        super(context);
    }

    public UpdateUseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public UpdateUseDialog(Context context, User user) {
        super(context);
        this.user = user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.dialog_lso);
        initView();
        initListener();
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edt_name);
        edAge = (EditText) findViewById(R.id.edt_age);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnOk = (Button) findViewById(R.id.btn_ok);
    }

    private void initListener() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.isEmptyString(edName.getText().toString())&&!Util.isEmptyString(edAge.getText().toString())){
                    updateUser = new User(user.getId(),edName.getText().toString(),Integer.valueOf(edAge.getText().toString()));
                    onBtnClickListener.onOkclick(updateUser);
                    dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void show() {
        super.show();
        initData(user);
    }

    private void initData(User user) {
        if (user!=null){
            edName.setText(user.getName());
            edAge.setText(user.getAge()+"");
        }else {
            edName.setText("");
            edAge.setText("");
        }
    }

}
