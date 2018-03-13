package com.twinly.enotices.enoticesapp.protocol;

import com.external.activeandroid.Model;

import org.json.JSONObject;

/**
 * Created by jbhuang on 2/6/2018.
 */

public class USER{
    public String Username;
    public String area_code;
    public String Password;

    public void fromJson(JSONObject object){
        this.Username=object.optString("username");
    }
}
