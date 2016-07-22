package widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
public class DatePickerPopupWindow {

    private RecyclerView recyv_poplist = null;

    private PopupwindowListAdapter adapter = null;

    private List<Bean> list = new ArrayList<>();

    private PopupWindow popupWindow = null;

    private Context context = null;

    private int layout_width = 0;

    private int layout_height = 0;

    public DatePickerPopupWindow(List<Bean> list, Context context, int width , int height) {
        if(list == null){
            setData();
        }
        else {
            this.list = list;
        }

        this.context = context;
        this.layout_width = width;
        this.layout_height = height ;
        init(context);
    }

    public void popWindowAtBottom(View parent){
        if(popupWindow != null){
            popupWindow.showAtLocation(parent,Gravity.BOTTOM,0,0);
        }
    }

    public boolean isShowing(){
        if(popupWindow != null){
            return popupWindow.isShowing();
        }
        return false;
    }

    public void close(){
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }

    private void init(Context context ){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selectdatelist,null);
        popupWindow = new PopupWindow(view ,layout_width ,layout_height);
        recyv_poplist = (RecyclerView) view.findViewById(R.id.recyv_listpop);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyv_poplist.setLayoutManager(manager);
        adapter = new PopupwindowListAdapter(list);
        recyv_poplist.setAdapter(adapter);
    }

    private void addList(List<Bean> newList){
        list.addAll(newList);
    }

    private void remove(int position){
        if(position < list.size() && position >= 0){
            list.remove(position);
        }
    }

    private void setData(){
        list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd", Locale.CHINESE);
        for (int i = 0; i < 5; i++) {
            Bean bean = new Bean();
            bean.setDate(format.format(calendar.getTime()));
            list.add(bean);
        }
    }
}
