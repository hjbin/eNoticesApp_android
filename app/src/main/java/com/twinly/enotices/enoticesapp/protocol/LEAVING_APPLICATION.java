package com.twinly.enotices.enoticesapp.protocol;

import org.json.JSONObject;

/**
 * Created by huangjinbin on 2018/3/9.
 */

public class LEAVING_APPLICATION {
    public String app_id;
    public String start_time;
    public String end_time;
    public String start_time_type;
    public String end_time_type;
    public String leaving_reason;
    public String state;
    public String is_read;
    public String type;
    public String create_time;
    public String response_time;
    public String withdraw_time;


    public void fromJson(JSONObject jsonObject){
        app_id=jsonObject.optString("app_id");
        start_time=jsonObject.optString("start_time");
        end_time=jsonObject.optString("end_time");
        start_time_type=jsonObject.optString("start_time_type");
        end_time_type=jsonObject.optString("end_time_type");
        leaving_reason=jsonObject.optString("leaving_reason");
        state=jsonObject.optString("state");
        is_read=jsonObject.optString("is_read");
        type=jsonObject.optString("type");
        create_time=jsonObject.optString("create_time");
        response_time=jsonObject.optString("response_time");
        withdraw_time=jsonObject.optString("withdraw_time");
    }
}
