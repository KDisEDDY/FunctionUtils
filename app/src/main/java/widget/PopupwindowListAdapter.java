package widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import project.ljy.functionutils.R;

/**
 * Title: PopupwindowListAdapter
 * Description:
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2016/7/19
 * Version: 1.0
 */
public class PopupwindowListAdapter extends RecyclerView.Adapter<PopupwindowListAdapter.ViewHolder> {

    private List<Bean> list = null;

    public PopupwindowListAdapter(List<Bean> list) {
            this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
               parent.getContext()).inflate(R.layout.recyclelist, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_recyclelist_date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_recyclelist_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_recyclelist_date = (TextView) itemView.findViewById(R.id.tv_recyclelist_date);
        }
    }
}
