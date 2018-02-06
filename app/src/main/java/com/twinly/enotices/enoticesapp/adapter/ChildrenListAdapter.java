package com.twinly.enotices.enoticesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.twinly.enotices.enoticesapp.R;

/**
 * Created by jbhuang on 2/6/2018.
 */

public class ChildrenListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public ChildrenListAdapter(Context ctx){
        this.context=ctx;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.item_children_list,null);
        return view;
    }
}
