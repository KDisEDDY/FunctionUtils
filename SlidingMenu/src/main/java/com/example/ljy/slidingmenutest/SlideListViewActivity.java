package com.example.ljy.slidingmenutest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljy.slidingmenutest.utils.ViewUtil;
import com.example.ljy.slidingmenutest.widget.DragLayout;
import com.example.ljy.slidingmenutest.widget.SlideLayout;

public class SlideListViewActivity extends AppCompatActivity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_list_view);
        layout  = (LinearLayout) findViewById(R.id.layout);
//        dynamicAddView(layout,3);
        DragLayout dragLayout = new DragLayout(this);
        dragLayout.addComponments(dynamicAddView(3));
        layout.addView(dragLayout);

//        dynamicAddView(layout,5);

    }

     private void  dynamicAddView(ViewGroup viewGroup, int size){
        for(int i = 0 ; i< size;i ++){
            SlideLayout slideLayout = new SlideLayout(this);
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setWidth(200);
            textView.setHeight(100);
            textView.setText("content");
            slideLayout.setContentView(textView);
            TextView menutext = new TextView(this);
            menutext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            menutext.setWidth(200);
            menutext.setText("delete");
            menutext.setHeight(100);
            slideLayout.setMenuView(menutext);
            viewGroup.addView(slideLayout);
        }
         viewGroup.invalidate();
     }

    private View[] dynamicAddView(int size){
        View[] views = new View[size];
        int ScreenWidth = ViewUtil.getScreenDispaly(this)[0];
        for (int i = 0; i < size; i++) {
            SlideLayout slideLayout = new SlideLayout(this);
            TextView textView = new TextView(this);
            textView.setWidth(ScreenWidth - 200);
            textView.setHeight(150);
            textView.setText("content");
            slideLayout.setContentView(textView);
            TextView menutext = new TextView(this);
            menutext.setWidth(200);
            menutext.setHeight(150);
            menutext.setText("delete");
            slideLayout.setMenuView(menutext);
            views[i] = slideLayout;
        }
        return views;
    }

}
