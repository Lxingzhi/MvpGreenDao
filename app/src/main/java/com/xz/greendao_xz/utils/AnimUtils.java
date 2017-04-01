package com.xz.greendao_xz.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.xz.greendao_xz.R;


public class AnimUtils {
	/**
	 * @Title: loadLeftToRightAnim
	 * @Description: 从左到右的动画（包含透明度）
	 * @param @param context
	 * @param @param view 设定文件
	 * @return void 返回类型
	 */
	public static void loadLeftToRightAnimShow(Context context, View view) {
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.left_to_right_show);
		view.startAnimation(animation);
	}

	public static void loadRightToLeftAnimHidden(Context context, View view) {
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.right_to_left_hidden);
		view.startAnimation(animation);
	}

	public static void loadLeftToRightAnimHidden(Context context, View view) {
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.left_to_right_hidden);
		view.startAnimation(animation);
	}

	public static void loadRightToLeftAnimShow(Context context, View view) {
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.right_to_left_show);
		view.startAnimation(animation);
	}
}
