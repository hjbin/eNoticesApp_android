package com.twinly.enotices.enoticesapp.protocol;

import org.json.JSONObject;

/**
 * Created by huangjinbin on 2018/2/14.
 */

public class NOTICE {
    public String notice_title;
    public String notice_content;
    public String reply_state;
    public String reply_time;
    public String unique_code;
    public String post_date;
    public String deadline;
    public String lastModifyTime;
    public String pdf_name;

    public void fromJson(JSONObject jsonObject){
        this.notice_title=jsonObject.optString("notice_title");
        this.notice_content=jsonObject.optString("result");
        this.post_date=jsonObject.optString("post_date");
        this.deadline=jsonObject.optString("deadline");
        this.reply_state=jsonObject.optString("reply_state");
        this.reply_time=jsonObject.optString("reply_time");
        this.unique_code=jsonObject.optString("unique_code");
        this.lastModifyTime=jsonObject.optString("last_modify_time");
        this.pdf_name=jsonObject.optString("pdf_name");
    }
}
