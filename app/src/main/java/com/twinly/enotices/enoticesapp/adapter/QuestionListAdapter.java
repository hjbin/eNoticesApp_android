package com.twinly.enotices.enoticesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.protocol.NOTICE_QUESTION;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huangjinbin on 2018/3/7.
 */

public class QuestionListAdapter extends BaseAdapter {

    private ArrayList<NOTICE_QUESTION> dataList =new ArrayList<NOTICE_QUESTION>();
    private Context mContext;
    private LayoutInflater mInflater;
    private HashMap<Integer,String> datas=new HashMap<Integer,String>();
    private int index=-1;
    private OnAnswerListener onAnswerListener;

    public QuestionListAdapter(Context ctx,ArrayList<NOTICE_QUESTION> questionList){
        mContext=ctx;
        dataList=questionList;
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
        final ViewHolder holder=new ViewHolder();

        if (dataList.get(i).ans_type.equals("O")){

            final int pos=i;
            view = mInflater.inflate(R.layout.item_question_open,null);
            holder.tv_question_content=view.findViewById(R.id.tv_question_content_open);
            holder.edt_question_ans=view.findViewById(R.id.edt_question_answer);

            holder.tv_question_content.setText(dataList.get(i).question_content);
            if (datas.get(pos)!=null){
                holder.edt_question_ans.setText(datas.get(pos));
            }

            holder.edt_question_ans.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b){
                        //holder.edt_question_ans.setText(holder.edt_question_ans.getText().toString());
                        //Toast.makeText(mContext,"[text]:"+holder.edt_question_ans.getText().toString(),Toast.LENGTH_LONG).show();
                        String temp_ans=holder.edt_question_ans.getText().toString();
                        datas.put(pos,temp_ans);
                        onAnswerListener.onAnswerOpen(pos,temp_ans);
                    }
                }
            });

            holder.edt_question_ans.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                        index=pos;
                    }
                    return false;
                }
            });

            holder.edt_question_ans.clearFocus();
            if (index!=-1 && index==i){
                holder.edt_question_ans.requestFocus();
                holder.edt_question_ans.setSelection(holder.edt_question_ans.getText().length());
                index=-1;
            }


        }else if(dataList.get(i).ans_type.equals("S")){
            final int q_pos=i;
            view =mInflater.inflate(R.layout.item_question_multiple,null);
            holder.tv_question_content=view.findViewById(R.id.tv_question_content_multiple);
            holder.rg_option_group=view.findViewById(R.id.rg_question_option);

            holder.tv_question_content.setText(dataList.get(i).question_content);
            for (int k=0;k<dataList.get(i).option_content.size();k++){
                RadioButton rbtn_option=new RadioButton(mContext);
                rbtn_option.setText(dataList.get(i).option_content.get(k));
                rbtn_option.setId(k);
                if (dataList.get(i).answer_single!=null){
                    if (dataList.get(i).answer_single.equals(dataList.get(i).option_id.get(k))){
                        rbtn_option.setChecked(true);
                    }
                }
                holder.rg_option_group.addView(rbtn_option);
            }

            holder.rg_option_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int id) {
                    Log.d("[QuestionListAdapter]","[radio button checked]: id: "+id);
                    onAnswerListener.onAnswerSingle(q_pos,id);
                }
            });


        }else{
            final int question_pos=i;
            view =mInflater.inflate(R.layout.item_question_multiple,null);
            holder.tv_question_content=view.findViewById(R.id.tv_question_content_multiple);
            holder.ll_check_group=view.findViewById(R.id.ll_check_group);

            holder.tv_question_content.setText(dataList.get(i).question_content);
            for (int k=0;k<dataList.get(i).option_content.size();k++){
                CheckBox cb_option=new CheckBox(mContext);
                cb_option.setText(dataList.get(i).option_content.get(k));
                cb_option.setId(k);
                if (dataList.get(i).answer_multiple.get(k)) {
                    cb_option.setChecked(true);
                }
                cb_option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Log.d("[QuestionListAdapter]","[check box state changed]: id: "+compoundButton.getId()+" | checked: "+b);
                        onAnswerListener.onAnswerMultiple(question_pos,compoundButton.getId(),b);
                    }
                });
                holder.ll_check_group.addView(cb_option);
            }
        }

        return view;
    }

    public void setOnAnswerListener(OnAnswerListener l){
        onAnswerListener=l;
    }

    class ViewHolder{
        TextView tv_question_content;
        EditText edt_question_ans;
        RadioGroup rg_option_group;
        LinearLayout ll_check_group;
    }
}
