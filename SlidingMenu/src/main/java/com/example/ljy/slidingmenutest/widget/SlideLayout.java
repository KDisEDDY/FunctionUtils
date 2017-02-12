package com.example.ljy.slidingmenutest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.ljy.slidingmenutest.R;
import com.example.ljy.slidingmenutest.utils.ViewUtil;

/**
 *自定义控件： 滑动删除的包裹Item的layout
 * Created by Eddy on 2016/6/11.
 */
public class SlideLayout extends LinearLayout {

    Context mContext;

    /***/
    Scroller mScroller;

    /**子item*/
    LinearLayout ll_content;

    /**删除按钮*/
    LinearLayout ll_slideview;

    int PreX = 0;

    /**可滑动距离*/
    int scrollX = 0;

    public SlideLayout(Context context) {
        this(context,null);
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = getContext();
        mScroller = new Scroller(mContext);
//        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        View.inflate(mContext,R.layout.slidelayout,this);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        ll_content.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll_slideview = (LinearLayout) findViewById(R.id.ll_slideview);
        ll_slideview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public void setContentView(View view){
        ll_content.addView(view);
    }

    public void setMenuView(View view){
        ll_slideview.addView(view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ll_slideview.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.UNSPECIFIED));
        ll_content.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.UNSPECIFIED));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed,l,t,r,b);
        ll_content.layout(0, 0, getMeasuredWidth(), ll_content.getMeasuredHeight());
        Log.e("slideLayout_root","width:" + ViewUtil.getViewWith(this) + " height:" + ViewUtil.getViewHeight(this));
        Log.e("slideLayout_root", getPaddingBottom() + "," + getPaddingTop());
        Log.e("slidelayout" ,"content data :" + "0 0 " + getMeasuredWidth() + " " + ll_content.getMeasuredHeight());
        ll_slideview.layout(getMeasuredWidth(), 0, ll_slideview.getMeasuredWidth(),
                ll_slideview.getMeasuredHeight());
        Log.e("slidelayout" ,"slidemenu data :" + getMeasuredWidth() +" 0 " +  ll_slideview.getMeasuredWidth() + " " + ll_slideview.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("show","slideview " + "left:" + ll_slideview.getLeft() + "top:" + ll_slideview.getTop() + "width:" + ll_slideview.getMeasuredWidth() + " height:" + ll_slideview.getHeight());
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                PreX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int deltaX = PreX - x;
                    swipe(deltaX);
                break;
            case MotionEvent.ACTION_UP:break;
            default:break;
        }
        return true;
    }

    private void swipe(int dis) {
        if (dis > ll_slideview.getMeasuredWidth()) {
            dis = ll_slideview.getMeasuredWidth();
        }
        if (dis < 0) {
            dis = 0;
        }
        ll_content.layout(-dis, ll_content.getTop(), ll_content.getWidth() - dis, ll_content.getMeasuredHeight());
        ll_slideview.layout(ll_content.getWidth() - dis, ll_slideview.getTop(),
                ll_content.getWidth() + ll_slideview.getMeasuredWidth() - dis, ll_slideview.getBottom());
        Log.e("swipe","left:" + (ll_content.getWidth() - dis) + " top:" + ll_slideview.getTop() + " right:" + (ll_content.getWidth() + ll_slideview.getMeasuredWidth() - dis) + " bottom:" + ll_slideview.getBottom());
    }

}
