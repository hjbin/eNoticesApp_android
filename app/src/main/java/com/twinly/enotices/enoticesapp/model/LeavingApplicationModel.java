package com.twinly.enotices.enoticesapp.model;

import android.content.Context;

import com.BeeFramework.model.BaseModel;
import com.BeeFramework.model.BeeCallback;
import com.BeeFramework.view.MyProgressDialog;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.LEAVING_APPLICATION;
import com.twinly.enotices.enoticesapp.protocol.MESSAGE;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huangjinbin on 2018/3/9.
 */

public class LeavingApplicationModel extends BaseModel {
    public ArrayList<LEAVING_APPLICATION> pendingList=new ArrayList<LEAVING_APPLICATION>();
    public ArrayList<LEAVING_APPLICATION> historyList=new ArrayList<LEAVING_APPLICATION>();

    public LeavingApplicationModel(Context context) {
        super(context);
    }

    public void getLeavingApplicationList(String school_db,String secret_id) {
        String url = Constants.GET_LEAVING_APPLICATION_LIST;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                LeavingApplicationModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    pendingList.clear();
                    historyList.clear();
                    try
                    {
                        JSONArray appArray = jo.optJSONArray("application_list");
                        for (int i = 0; i < appArray.length(); i++)
                        {
                            JSONObject jsonItem = appArray.getJSONObject(i);
                            LEAVING_APPLICATION appItem =new LEAVING_APPLICATION();
                            appItem.fromJson(jsonItem);
                            if (appItem.state.equals("0")){
                                pendingList.add(appItem);
                            }else {
                                historyList.add(appItem);
                            }
                        }

                        LeavingApplicationModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("secret_id",secret_id);
        params.put("school_db",school_db);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void addLeavingApplication(String school_db,String secret_id,String contact_phone,LEAVING_APPLICATION leaving) {
        String url = Constants.ADD_LEAVING_APPLICATION;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                LeavingApplicationModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try {
                        LeavingApplicationModel.this.OnMessageResponse(url, jo, status);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("secret_id",secret_id);
        params.put("school_db",school_db);
        params.put("start_time",leaving.start_time);
        params.put("end_time",leaving.end_time);
        params.put("start_time_type",leaving.start_time_type);
        params.put("end_time_type",leaving.end_time_type);
        params.put("leaving_reason",leaving.leaving_reason);
        params.put("contact_phone",contact_phone);
        params.put("type",leaving.type);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }


    public void withdrawLeavingApplication(String school_db,String app_id) {
        String url = Constants.ADD_LEAVING_APPLICATION;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                LeavingApplicationModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try {
                        LeavingApplicationModel.this.OnMessageResponse(url, jo, status);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("app_id",app_id);
        params.put("school_db",school_db);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }


}
