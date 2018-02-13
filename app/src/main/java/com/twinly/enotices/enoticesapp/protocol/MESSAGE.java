package com.twinly.enotices.enoticesapp.protocol;

import org.json.JSONObject;

/**
 * Created by jbhuang on 2/13/2018.
 */

public class MESSAGE {
    public String id;
    public String event_type;
    public String post_date;
    public String deadline;
    public String state;
    public String starred;
    public String title;

    public void fromJson(JSONObject jsonObject){
        this.id=jsonObject.optString("id");
        this.event_type=jsonObject.optString("event_type");
        this.deadline=jsonObject.optString("deadline");
        this.state=jsonObject.optString("state");
        this.title=jsonObject.optString("title");
        this.post_date=jsonObject.optString("post_date");
        this.starred=jsonObject.optString("starred");
    }
}
