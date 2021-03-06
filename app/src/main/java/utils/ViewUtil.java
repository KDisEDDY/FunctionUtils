/**
 * Title: ViewUtil.java
 * Description:
 * Copyright: Copyright (c) 2013 luoxudong.com
 * Company:个人
 * Author 罗旭东 (hi@luoxudong.com)
 * Date 2013-10-11 下午2:24:40
 * Version 1.0
 */
package utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

public class ViewUtil {
	/**
	 * 获取控件宽度
	 */
	public static int getViewWith(View v){
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
		v.measure(w, h);  
		return v.getMeasuredWidth();
	}
	
	/**
	 * 获取控件高度
	 * @param v
	 */
	public static int getViewHeight(View v){
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
		v.measure(w, h);  
		return v.getMeasuredHeight();
	}
	
	public static int getLayoutX(View view){
		int[] location = new int[2] ;
		Rect rect = new Rect();
		view.getLocalVisibleRect(rect);
		view.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
		view.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
		return location [0];
	}
	
	public static int getLayoutY(View view){
		int[] location = new int[2] ;
		view.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
		view.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
		return location [1];
	}
	
	/* 
     * 设置控件所在的位置X，并且不改变宽高， 
     * X为绝对位置，此时Y可能归0 
     */  
	public static void setLayoutX(View view, int x) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(x, margin.topMargin, x + margin.width,
				margin.bottomMargin);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}
    
    /* 
     * 设置控件所在的位置Y，并且不改变宽高， 
     * Y为绝对位置，此时X可能归0 
     */  
	public static void setLayoutY(View view, int y) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(margin.leftMargin, y, margin.rightMargin, y
				+ margin.height);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}
    
    /* 
     * 设置控件所在的位置YY，并且不改变宽高， 
     * XY为绝对位置 
     */  
	public static void setLayout(View view, int x, int y) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(x, y, 0, 0);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	} 
    
	/**
	 * 显示软键盘
	 * 
	 */
	public static void showInputMethod(Activity activity, View view) {
		InputMethodManager imm = (InputMethodManager) activity.getBaseContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, 0);
	}
	
	/**
	 * 隐藏软键盘
	 * @param activity
	 */
	public static void hiddenInputMethod(Activity activity) {
		// toggleSoftInput(activity);
		if (activity != null) {
			try {
				InputMethodManager imm = (InputMethodManager) activity
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(activity.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			} catch (Exception e) {
			}

		}
	}
	
	/**
	 * 显示或隐藏软键盘(如果输入法在窗口上已经显示，则隐藏，反之则显示)
	 * @param context
	 * @return void
	 */
	public static void toggleSoftInput(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	
	/**
	 * 获取屏幕尺寸
	 * @param context
	 * @return
	 */
	public static int[] getScreenDispaly(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		//int width = windowManager.getDefaultDisplay().getWidth();
		//int height = windowManager.getDefaultDisplay().getHeight();
		Point mPoint = new Point();
		windowManager.getDefaultDisplay().getSize(mPoint);
		int width = mPoint.x;
		int height = mPoint.y;
		int result[] = { width, height };
		return result;
	}
}
