package widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
public class PopupwindowListAdapter<T> extends RecyclerView.Adapter<PopupwindowListAdapter.ViewHolder> {

    private List<T> list = null;

    public PopupwindowListAdapter(List<T> list) {
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_recyclelist_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_recyclelist_date = (TextView) itemView.findViewById(R.id.tv_recyclelist_date);
        }
    }
}
