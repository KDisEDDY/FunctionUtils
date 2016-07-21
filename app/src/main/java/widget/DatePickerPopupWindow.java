package widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import project.ljy.functionutils.R;

/**
 * Title: DatePickerPopupWindow
 * Description:带listview的日期选择框
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2016/7/19
 * Version: 1.0
 */
public class DatePickerPopupWindow<T> extends PopupWindow {

    private RecyclerView recyv_poplist = null;

    private PopupwindowListAdapter adapter = null;

    private List<T> list = new ArrayList<>();

    public DatePickerPopupWindow(Context context) {
        super(context);
        init(context);
    }

    public DatePickerPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatePickerPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context ){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selectdatelist,null);
        this.setContentView(view);
        recyv_poplist = (RecyclerView) view.findViewById(R.id.recyv_listpop);
        adapter = new PopupwindowListAdapter<>(list);
        recyv_poplist.setAdapter(adapter);

    }

    private void addList(List<T> newList){
        list.addAll(newList);
    }

    private void remove(int position){
        if(position < list.size() && position >= 0){
            list.remove(position);
        }
    }

}
