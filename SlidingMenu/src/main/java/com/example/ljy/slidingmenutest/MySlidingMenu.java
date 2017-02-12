package com.example.ljy.slidingmenutest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MySlidingMenu extends HorizontalScrollView {
    ViewGroup Menu,Content;                                //侧滑菜单界面和主界面
    private int MenuWidth;
    private int mScreenWidth;                            //屏宽
    private int R_distance ;
    private boolean once ;

    public MySlidingMenu(Context context) {
        this(context,null);
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context ,attrs, 0);

    }

    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleRes) {
        super(context, attrs,defStyleRes);
        //自定义属性的获取
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MySlidingMenu,defStyleRes,0);
        int temp_R_distance = (int)a.getDimension(R.styleable.MySlidingMenu_rightpadding,100);
        once = false;

        R_distance = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, temp_R_distance, context
                        .getResources().getDisplayMetrics());
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenWidth = displayMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!once){
            LinearLayout layout = (LinearLayout) getChildAt(0);
            Menu = (ViewGroup) layout.getChildAt(0);
            Content = (ViewGroup) layout.getChildAt(1);
            MenuWidth = Menu.getLayoutParams().width = mScreenWidth - R_distance;
            Content.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(MenuWidth,0);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if(action==MotionEvent.ACTION_UP){
           if(getScrollX() <= MenuWidth /2){
               smoothScrollTo(MenuWidth,0);
           }
            else {
               smoothScrollTo(0,0);
           }
            return false;
        }
        return true;
    }
}
