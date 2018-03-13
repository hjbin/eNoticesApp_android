package com.twinly.enotices.enoticesapp.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by huangjinbin on 2018/3/7.
 */

public class NOTICE_QUESTION {

    public String question_id;
    public String ans_type;
    public String question_content;
    public String reply_id;
    public ArrayList<String> option_content=new ArrayList<String>();
    public ArrayList<String> option_id=new ArrayList<String>();

    public String answer_text;
    public String answer_single;
    public ArrayList<Boolean> answer_multiple=new ArrayList<Boolean>();

    public void fromJson(JSONObject jo) throws JSONException {
        question_id=jo.optString("question_id");
        question_content=jo.optString("question_content");
        ans_type=jo.optString("ans_type");
        reply_id=jo.optString("reply_id");
        JSONArray oc=jo.optJSONArray("option_content");
        for (int i=0;i<oc.length();i++){
                option_content.add(oc.getString(i));
                answer_multiple.add(false);
        }

        JSONArray oids=jo.optJSONArray("option_id");
        for (int i=0;i<oids.length();i++){
            option_id.add(oids.getString(i));
        }
    }

}
