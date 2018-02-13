package com.twinly.enotices.enoticesapp.adapter;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.twinly.enotices.enoticesapp.R;

/**
 * Created by jbhuang on 2/13/2018.
 */

public class UnreadNoticeListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    public UnreadNoticeListAdapter(Context context){
        mContext=context;
        mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return 5;
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
        view=mInflater.inflate(R.layout.item_notice,null);
        return view;
    }
}
