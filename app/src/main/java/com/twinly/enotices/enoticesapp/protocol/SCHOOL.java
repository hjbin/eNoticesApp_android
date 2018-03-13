package com.twinly.enotices.enoticesapp.protocol;

import org.json.JSONObject;

/**
 * Created by huangjinbin on 2018/3/13.
 */

public class SCHOOL {
    public String leaving_application;
    public String school_bus;
    public String dining_order;
    public String teacher_appointment;
    public String temperature_report;

    public void fromJson(JSONObject jsonObject){
        leaving_application=jsonObject.optString("leaving_application");
        school_bus=jsonObject.optString("school_bus");
        dining_order=jsonObject.optString("dining_order");
        teacher_appointment=jsonObject.optString("teacher_appointment");
        temperature_report=jsonObject.optString("temperature_report");
    }

}
