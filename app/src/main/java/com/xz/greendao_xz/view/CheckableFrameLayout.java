package com.xz.greendao_xz.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * @ClassName: CheckableFrameLayout
 * @Description: 自定义可选的控件
 * @author ghy
 * @date 2016-7-5 下午1:50:30
 */
public class CheckableFrameLayout extends FrameLayout implements Checkable {

	public CheckableFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	public CheckableFrameLayout(Context context, AttributeSet attrs,
								int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CheckableFrameLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	private boolean mChecked = false;

	@Override
	public void toggle() {
		setChecked(!mChecked);
	}

	@Override
	public boolean isChecked() {
		return mChecked;
	}

	@Override
	public void setChecked(boolean checked) { // 此函数会有listview 设置
		if (mChecked != checked) {
			mChecked = checked;
			refreshDrawableState();
			for (int i = 0, len = getChildCount(); i < len; i++) {
				View child = getChildAt(i);
				if (child instanceof Checkable) {
					((Checkable) child).setChecked(checked);
				}
			}
		}
	}

}
