package com.example.ljy.slidingmenutest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.ljy.slidingmenutest.utils.ViewUtil;

/**
 * Created by Administrator on 2015/9/17.
 */
public class MySlidingMenu extends ViewGroup {

    ViewGroup mMenu, mContent;                                //侧滑菜单界面和主界面
    private int mMenuWidth;
    private int mScreenWidth;                            //屏宽
    private int mScreenHeight;                           //屏高

    private int mRdistance;
    private boolean mHasFirstLoad;

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
        mRdistance = (int)a.getDimension(R.styleable.MySlidingMenu_rightpadding,100);
        mHasFirstLoad = false;

//        mRdistance = (int)TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP, tempDistance, context
//                        .getResources().getDisplayMetrics());
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        //todo:widthPixels，heightPixels和其他的获取屏幕宽高的区别
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        if(!mHasFirstLoad){
//            LinearLayout layout = (LinearLayout) getChildAt(0);
//            mMenu = (ViewGroup) layout.getChildAt(0);
//            mContent = (ViewGroup) layout.getChildAt(1);
//            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mRdistance;
//            mContent.getLayoutParams().width = mScreenWidth;
//            mHasFirstLoad = true;
//        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(!mHasFirstLoad){
            mMenu = (ViewGroup) getChildAt(0);
            mContent = (ViewGroup) getChildAt(1);
            mMenuWidth = mRdistance;
            mMenu.getLayoutParams().width = mRdistance;
            mMenu.getLayoutParams().height = mScreenHeight;
            mContent.getLayoutParams().width = mScreenWidth;
            mContent.getLayoutParams().height = mScreenHeight;
            mHasFirstLoad = true;
            setLayoutParams(new RelativeLayout.LayoutParams(widthSize,heightSize));
            mContent.measure(widthMeasureSpec,heightMeasureSpec);
            mMenu.measure(widthMeasureSpec,heightMeasureSpec);
        }
        setMeasuredDimension(widthSize , heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            mMenu.scrollTo(-mMenuWidth,0);
        }
        mContent.layout(l,t,r,b);
        mMenu.layout(l,t,mMenuWidth,b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if(action==MotionEvent.ACTION_UP){
           if(getScrollX() <= mMenuWidth /2){
               mMenu.scrollTo(0,0);
           } else {
               mMenu.scrollTo(-mMenuWidth,0);
           }
            return false;
        }
        return true;
    }
}
