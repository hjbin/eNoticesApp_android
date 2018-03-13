package com.twinly.enotices.enoticesapp.protocol;

import org.json.JSONObject;

/**
 * Created by huangjinbin on 2018/3/7.
 */

public class ACTIVITY {

    public String act_id;
    public String act_title;
    public String read_state;
    public String act_post_date;
    public String starred;
    public String del_time;
    public String event_type;

    public void fromJson(JSONObject jsonObject){
        act_id=jsonObject.optString("act_id");
        act_title=jsonObject.optString("act_title");
        read_state=jsonObject.optString("read_state");
        act_post_date=jsonObject.optString("act_post_date");
        starred=jsonObject.optString("starred");
        del_time=jsonObject.optString("del_time");
        event_type=jsonObject.optString("event_type");
    }
}
