package com.twinly.enotices.enoticesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.BeeFramework.BeeFrameworkApp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.CHILDREN;

import java.util.ArrayList;

/**
 * Created by jbhuang on 2/6/2018.
 */

public class ChildrenListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private OnPushStateChangeListener listener;
    private ArrayList<CHILDREN> datalist=new ArrayList<CHILDREN>();
    public ChildrenListAdapter(Context ctx, ArrayList<CHILDREN> dlist){
        this.context=ctx;
        this.inflater=LayoutInflater.from(context);
        this.datalist=dlist;
    }

    public void setOnPushStateChangedListener(OnPushStateChangeListener l){
        listener=l;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return datalist.get(i);
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
            view=inflater.inflate(R.layout.item_children_list,null);
            holder.iv_children_logo=view.findViewById(R.id.iv_children_icon);
            holder.sw_push=view.findViewById(R.id.sw_push);
            holder.tv_unread_act=view.findViewById(R.id.tv_children_activity);
            holder.tv_unread_notice=view.findViewById(R.id.tv_children_notice);
            holder.tv_unread_announce=view.findViewById(R.id.tv_children_announcement);
            holder.tv_children_name=view.findViewById(R.id.tv_children_name);


            String imageUri=datalist.get(i).school_domain+ Constants.BASE_IMAGE_ADD+datalist.get(i).image;
            Log.i("[ChildrenListAdapter]","[imageUri]: "+imageUri);
            Picasso.with(context).load(imageUri).fit().into(holder.iv_children_logo);;
            //ImageLoader.getInstance().displayImage(,holder.iv_children_logo, BeeFrameworkApp.options_head);
            holder.tv_children_name.setText(datalist.get(i).name);
            holder.tv_unread_notice.setText("("+datalist.get(i).unread_notice+")");
            holder.tv_unread_act.setText("("+datalist.get(i).unread_act+")");
            holder.tv_unread_announce.setText("("+datalist.get(i).unread_announce+")");

            if (datalist.get(i).push_alert.equals("Y")){
                holder.sw_push.setChecked(true);
            }else{
                holder.sw_push.setChecked(false);
            }
            final int pos=i;
            holder.sw_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (listener!=null){
                        listener.onPushStateChange(datalist.get(pos),b);
                    }
                }
            });

            view.setTag(holder);

        }else {
            holder=(ViewHolder)view.getTag();
        }
        return view;
    }

    class ViewHolder{
        ImageView iv_children_logo;
        TextView tv_children_name;
        TextView tv_unread_act;
        TextView tv_unread_notice;
        TextView tv_unread_announce;
        Switch sw_push;

    }
}
