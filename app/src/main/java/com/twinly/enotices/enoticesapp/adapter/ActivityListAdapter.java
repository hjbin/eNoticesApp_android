package com.twinly.enotices.enoticesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.protocol.ACTIVITY;

import java.util.ArrayList;

/**
 * Created by huangjinbin on 2018/3/7.
 */

public class ActivityListAdapter extends BaseAdapter {

    private ArrayList<ACTIVITY> dataList=new ArrayList<ACTIVITY>();
    private Context mContext;
    private LayoutInflater mInflater;

    public ActivityListAdapter(Context ctx, ArrayList<ACTIVITY> list){
        dataList=list;
        mContext=ctx;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=mInflater.inflate(R.layout.item_notice,null);
            holder.tv_post_date=view.findViewById(R.id.tv_item_date);
            holder.tv_title=view.findViewById(R.id.tv_item_title);
            holder.tv_status=view.findViewById(R.id.tv_item_state);
            holder.iv_image=view.findViewById(R.id.iv_item_logo);



            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }

        holder.tv_title.setText(dataList.get(i).act_title);
        holder.tv_post_date.setText(dataList.get(i).act_post_date);
        if (dataList.get(i).event_type.equals("null")){

            Picasso.with(mContext).load(R.mipmap.btn_notice_active_2x).fit().into(holder.iv_image);
            if (dataList.get(i).read_state.equals("1")){
                holder.tv_status.setText("Unread");
            }else {
                holder.tv_status.setText("Read");
            }
        }else{
            if (dataList.get(i).event_type.equals("E")){
                Picasso.with(mContext).load(R.mipmap.btn_event_active_2x).fit().into(holder.iv_image);
            }else {
                Picasso.with(mContext).load(R.mipmap.btn_announ_active2x).fit().into(holder.iv_image);
            }
            holder.tv_status.setText("Unread");
        }

        return view;
    }

    class ViewHolder{
        TextView tv_title;
        TextView tv_post_date;
        TextView tv_status;
        ImageView iv_image;
    }
}
