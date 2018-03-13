package com.twinly.enotices.enoticesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.protocol.LEAVING_APPLICATION;
import com.twinly.enotices.enoticesapp.utils.device.DeviceUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by huangjinbin on 2018/3/9.
 */

public class LeavingApplicationListAdapter extends BaseAdapter {
    private ArrayList<LEAVING_APPLICATION> dataList=new ArrayList<LEAVING_APPLICATION>();
    private Context mContext;
    private LayoutInflater mInflater;
    private OnWithdrawListener l;

    public LeavingApplicationListAdapter(Context ctx,ArrayList<LEAVING_APPLICATION> appList){
        dataList=appList;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=mInflater.inflate(R.layout.item_leaving_application,null);
            holder.tv_type=view.findViewById(R.id.tv_leaving_type);
            holder.tv_reason=view.findViewById(R.id.tv_leaving_reason);
            holder.tv_status=view.findViewById(R.id.tv_leaving_status);
            holder.tv_start=view.findViewById(R.id.tv_leaving_start);
            holder.tv_end=view.findViewById(R.id.tv_leaving_end);
            holder.tv_response_time=view.findViewById(R.id.tv_response_time);
            holder.btn_withdraw=view.findViewById(R.id.btn_leaving_withdraw);
            holder.ll_background=view.findViewById(R.id.ll_item_leaving);

            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        if (dataList.get(i).type.equals("0")){
            holder.tv_type.setText("Personal Leave");
        }else {
            holder.tv_type.setText("Sick Leave");
        }

        if (dataList.get(i).leaving_reason!=null){
            if (!dataList.get(i).leaving_reason.equals("")){
                holder.tv_reason.setText(dataList.get(i).leaving_reason);
            }
        }else {
            holder.tv_reason.setText("");
        }

        if (dataList.get(i).state.equals("0")){
            holder.ll_background.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.tv_status.setText("Processing");
            holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.colorProcessing));
            holder.tv_response_time.setVisibility(View.GONE);
            if (DeviceUtils.isDateBigger(dataList.get(i).start_time)){
                holder.btn_withdraw.setVisibility(View.VISIBLE);
                holder.btn_withdraw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (l!=null){
                            l.onWithdraw(dataList.get(i).app_id);
                        }
                    }
                });
            }
        }else if (dataList.get(i).state.equals("1")){
            holder.ll_background.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.tv_status.setText("Approved");
            holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.colorApprove));
            holder.tv_response_time.setVisibility(View.VISIBLE);
            holder.tv_response_time.setText("Response Time: "+dataList.get(i).response_time);
            holder.btn_withdraw.setVisibility(View.GONE);
        }else if (dataList.get(i).state.equals("2")){
            holder.ll_background.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.tv_status.setText("Rejected");
            holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.colorReject));
            holder.tv_response_time.setVisibility(View.VISIBLE);
            holder.tv_response_time.setText("Response Time: "+dataList.get(i).response_time);
            holder.btn_withdraw.setVisibility(View.GONE);
        }else if (dataList.get(i).state.equals("3")){
            holder.ll_background.setBackgroundColor(mContext.getResources().getColor(R.color.colorWithdrawBack));
            holder.tv_status.setText("Withdrawn");
            holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.colorWithdraw));
            holder.tv_response_time.setVisibility(View.VISIBLE);
            holder.tv_response_time.setText("Withdrawn Time: "+dataList.get(i).withdraw_time);
            holder.btn_withdraw.setVisibility(View.GONE);
        }

        if (dataList.get(i).start_time_type.equals("0")){
            holder.tv_start.setText("Start: "+dataList.get(i).start_time+" AM");
        }else {
            holder.tv_start.setText("Start: "+dataList.get(i).start_time+" PM");
        }

        if (dataList.get(i).end_time_type.equals("0")){
            holder.tv_end.setText("End: "+dataList.get(i).end_time+" AM");
        }else {
            holder.tv_end.setText("End: "+dataList.get(i).end_time+" PM");
        }

        return view;
    }

    public void setOnWithdrawListener(OnWithdrawListener listener){
        l=listener;
    }

    class ViewHolder{
        TextView tv_status;
        TextView tv_type;
        TextView tv_reason;
        TextView tv_start;
        TextView tv_end;
        TextView tv_response_time;
        Button btn_withdraw;
        LinearLayout ll_background;
    }
}
