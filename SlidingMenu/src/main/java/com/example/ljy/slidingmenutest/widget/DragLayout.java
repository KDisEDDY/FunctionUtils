package com.example.ljy.slidingmenutest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Title: DragLayout
 * Description:
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2016/6/17
 * Version: 1.0
 */
public class DragLayout extends LinearLayout {

    Context context;

    public DragLayout(Context context) {
        this(context,null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for(int i = 0 ;i < getChildCount() ; i++){
            View view = getChildAt(i);
            view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.UNSPECIFIED));
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int disTop = 0;
        for(int i = 0 ; i < getChildCount() ; i++ ){
            View view = getChildAt(i);
            view.layout(0,disTop,getMeasuredWidth(),view.getMeasuredHeight() + disTop);
            disTop += view.getMeasuredHeight();
            Log.e("layout" , i + " layout" + " left:" + 0 + " " + " top:" + disTop + " right:" + getMeasuredWidth() + "bottom:" + view.getMeasuredHeight());
        }
    }

    public void addComponment(View view){
        if(view != null){
            addView(view);
        }
        postInvalidate();
    }

    public void addComponments(View[] views){
        for(View view : views){
            if(view != null){
                addView(view);
            }
        }
        postInvalidate();
    }
}
