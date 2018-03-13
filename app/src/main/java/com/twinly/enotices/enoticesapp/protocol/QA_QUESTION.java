package com.twinly.enotices.enoticesapp.protocol;

import org.json.JSONObject;

/**
 * Created by huangjinbin on 2018/3/9.
 */

public class QA_QUESTION {

    public String question_content;
    public String question_answer;
    public String last_modify_time;

    public void fromJson(JSONObject jsonObject){
        question_content=jsonObject.optString("question_content");
        question_answer=jsonObject.optString("question_answer");
        last_modify_time=jsonObject.optString("last_modify_time");
    }
}
