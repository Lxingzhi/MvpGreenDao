package com.xz.greendao_xz.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.xz.greendao_xz.R;

/**
 * lys
 * fragment是否加入到回退栈的工具类
 */

public class FragementManangerUtils {
    private static FragmentActivity context;
    private static int contentView;

    public static void init(FragmentActivity activity, int view) {
        // TODO Auto-generated method stub
        context = activity;
        contentView = view;

    }

    public static void addFragement(Fragment fragment, boolean isCanBack) {
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        // 首次添加的这种不需要添加动画

        transaction.add(contentView, fragment); // 动画设置
        if (isCanBack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void addFragementAnim(Fragment fragment, boolean isCanBack) {
        if (fragment != null && fragment.isAdded()) {
            return;
        }
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.fragement_in_from_left,   //第一个是点击进入是的位置，第二个是切换的时候离去的位置，
                R.anim.fragement_out_from_left, R.anim.fragement_in_from_right,
                R.anim.fragement_out_from_right); // 动画要在动作之前
        transaction.add(contentView, fragment); // 动画设置
        Log.i("lys", "addFragementAnim");
        if (isCanBack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void showFragement(Fragment fragment, boolean isCanBack) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.fragement_in_from_left,
                R.anim.fragement_out_from_left, R.anim.fragement_in_from_right,
                R.anim.fragement_out_from_right); // 动画要在动作之前
        transaction.show(fragment); // 动画设置
        if (isCanBack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void hiddenFragement(Fragment fragment, boolean isCanBack) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.fragement_in_from_left,
                R.anim.fragement_out_from_left, R.anim.fragement_in_from_right,
                R.anim.fragement_out_from_right); // 动画要在动作之前
        transaction.hide(fragment); // 动画设置
        if (isCanBack)
            transaction.addToBackStack(null);// 加入回退栈
        transaction.commitAllowingStateLoss();
    }

    public static void hiddenFlingRegister(Fragment fragment, boolean isCanBack) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.fragement_in_from_right,
                R.anim.fragement_out_from_right, R.anim.fragement_in_from_left,
                R.anim.fragement_out_from_left); // 动画要在动作之前
        transaction.hide(fragment); // 动画设置
        if (isCanBack)
            transaction.addToBackStack(null);// 加入回退栈
        transaction.commit();
    }

    public static void hiddenFragementToRight(Fragment fragment,
                                              boolean isCanBack) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        transaction
                .setCustomAnimations(R.anim.fragement_in_from_left,
                        R.anim.fragement_out_from_right,
                        R.anim.fragement_in_from_right,
                        R.anim.fragement_out_from_right); // 动画要在动作之前
        transaction.hide(fragment); // 动画设置
        if (isCanBack)
            transaction.addToBackStack(null);// 加入回退栈
//		transaction.commit(); // 不需要fragement 保存的状态，使用另一种
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏并且添加fragment  需要把注释那句打开。
     * R.id.fragment 布局
     */
    public static void hiddenAndAddFragement(Fragment hiddenFragement,
                                             Fragment showFragement, boolean isCanBack) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.fragement_in_from_left,
                R.anim.fragement_out_from_left, R.anim.fragement_in_from_right,
                R.anim.fragement_out_from_right); // 动画要在动作之前
        transaction.hide(hiddenFragement); // 动画设置
//        transaction.add(R.id.content_fragement, showFragement); // 动画设置
        if (isCanBack)
            transaction.addToBackStack(null);
        transaction.commit();

    }
}
