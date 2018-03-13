package com.twinly.enotices.enoticesapp.adapter;

/**
 * Created by huangjinbin on 2018/3/8.
 */

public interface OnAnswerListener {

    public void onAnswerOpen(int question_pos,String answer);
    public void onAnswerSingle(int question_pos,int option_pos);
    public void onAnswerMultiple(int question_pos,int option_pos,boolean isChecked);
}
