package project.ljy.clicknablespantext;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

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

    SpannableString mSpannable;

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
        mSpannable = new SpannableString(string);
        setLinkTextColor(getResources().getColor(R.color.btn_text_blue));
        mSpannable.setSpan(new Clickable(mOnClickListener), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(mSpannable);
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(getResources().getColor(android.R.color.transparent));
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
