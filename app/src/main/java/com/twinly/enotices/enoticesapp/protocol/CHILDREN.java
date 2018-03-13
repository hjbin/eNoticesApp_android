package com.twinly.enotices.enoticesapp.protocol;

import android.net.Uri;

import org.json.JSONObject;

/**
 * Created by huangjinbin on 2018/2/14.
 */

public class CHILDREN {
    public String secret_id;
    public String school_id;
    public String school_db;
    public String school_domain;
    public String birth_date;

    public String unread_notice;
    public String unread_announce;
    public String unread_act;
    public String push_alert;
    public String image;
    public String name;
    public String logo;

    public void fromJson(JSONObject jo){
        this.secret_id=jo.optString("secret_id");
        this.school_id=jo.optString("school_id");
        this.school_domain= Uri.decode(jo.optString("school_domain"));
        this.school_db=jo.optString("school_db");
        this.birth_date=jo.optString("birth_date");
    }

    public void fromJsonSchool(JSONObject jo){

        this.secret_id=jo.optString("secret");
        this.school_domain= Uri.decode(jo.optString("school_domain"));
        this.school_db=jo.optString("school_db");
        this.birth_date=jo.optString("date_birth");

        this.logo=jo.optString("logo");
        this.name=jo.optString("name");
        this.image=Uri.decode(jo.optString("image"));
        this.unread_act=jo.optString("unread_act");
        this.unread_announce=jo.optString("unread_announce");
        this.unread_notice=jo.optString("unread_notice");
        push_alert=jo.optString("push_alert");

    }
}
