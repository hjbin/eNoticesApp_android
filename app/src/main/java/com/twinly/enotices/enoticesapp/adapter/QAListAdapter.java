package com.twinly.enotices.enoticesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.external.activeandroid.util.Log;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.protocol.QA_QUESTION;

import java.util.ArrayList;

/**
 * Created by huangjinbin on 2018/3/9.
 */

public class QAListAdapter extends BaseAdapter {

    private ArrayList<QA_QUESTION> qaList=new ArrayList<QA_QUESTION>();
    private Context mContext;
    private LayoutInflater mInflater;

    public QAListAdapter(Context ctx,ArrayList<QA_QUESTION> dataList){
        qaList=dataList;
        mContext=ctx;
        mInflater=LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return qaList.size();
    }

    @Override
    public Object getItem(int i) {
        return qaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("[QAListAdapter]","[getView]");
        final ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=mInflater.inflate(R.layout.item_qa_question,null);
            holder.tv_question=view.findViewById(R.id.tv_qa_question_content);
            holder.tv_answer=view.findViewById(R.id.tv_qa_question_answer);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        holder.tv_question.setText(qaList.get(i).question_content);
        holder.tv_answer.setText(qaList.get(i).question_answer);

        return view;
    }


    class ViewHolder{
        TextView tv_question;
        TextView tv_answer;
    }
}
