package com.example.ljy.slidingmenutest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ljy.slidingmenutest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    ListView list;
    ArrayList<String> mArrayList = new ArrayList<>();
    public Fragment2() {
        // Required empty public constructor
    }
    private ArrayList<String> getData() {
        mArrayList.add("测试数据1");
        mArrayList.add("测试数据2");
        mArrayList.add("测试数据3");
        mArrayList.add("测试数据4");
        mArrayList.add("测试数据5");
        mArrayList.add("测试数据6");
        mArrayList.add("测试数据7");
        mArrayList.add("测试数据8");
        mArrayList.add("测试数据9");
        mArrayList.add("测试数据10");
        mArrayList.add("测试数据11");
        mArrayList.add("测试数据12");
        return mArrayList;
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        list = (ListView)view.findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_expandable_list_item_1, getData()));
        return view;
    }

}
