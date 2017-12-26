package project.ljy.clicknablespantext;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: ClicknableSpanText
 * Description:封装可点击spanText
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2017/4/1
 * Version: 1.0
 */
public class ClicknableSpanTextView extends android.support.v7.widget.AppCompatTextView {

    private OnClickListener mOnClickListener;

    SpannableStringBuilder mSpannable;

    public ClicknableSpanTextView(Context context) {
        super(context);
    }

    public ClicknableSpanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClicknableSpanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSpanText(String string, int start, int end) {
        //避免设置了不符合长度的字符串导致崩溃
        if(string.length() < end || start < 0){
            return ;
        }
        mSpannable = new SpannableStringBuilder(string);
        mSpannable.setSpan(new ForegroundColorSpan(getLinkTextColors().getDefaultColor()), start , end , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        int[] underLineSection = calculateUnderlineSection(string ,"[\\u4e00-\\u9fa5|\\d+|-]+" , start , end);
        mSpannable.setSpan(new Clickable(mOnClickListener), underLineSection[0], underLineSection[1], Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        setText(mSpannable);
        setMovementMethod(MyLinkMovementMethod.getInstance());
        setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    /**
     * 计算不包含书名号等非汉字，数字以及-号的下划线区间
     * @param string 全长字符串
     * @param matchStr 表达式规则
     * @param start 颜色字符串在全长字符串中的起始位置
     * @param end 颜色字符串在全长字符串中的终止位置
     * @return 下划线区间
     */
    private int[] calculateUnderlineSection(String string , String matchStr , int start  ,int end){
        int[] section = new int[2];
        Pattern p = Pattern.compile(matchStr);
        Matcher m = p.matcher(string.substring(start , end));
        if(m.find()){
            section[0] = m.start();
            section[1] = m.end();
        }
        return section;
    }

    public void setSpanClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

    /** 客服部分可点击类    */
    private class Clickable extends ClickableSpan implements OnClickListener {
        private final OnClickListener mListener;

        public Clickable(OnClickListener l) {
            mListener = l;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
    }
}
