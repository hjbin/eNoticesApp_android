package com.twinly.enotices.enoticesapp.model;

import android.content.Context;

import com.BeeFramework.model.BaseModel;
import com.BeeFramework.model.BeeCallback;
import com.BeeFramework.view.MyProgressDialog;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.MESSAGE;
import com.twinly.enotices.enoticesapp.protocol.USER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by huangjinbin on 2018/2/14.
 */

public class UserModel extends BaseModel {
    public USER user;
    public String app_version;
    public String os_name;
    public String os_version;

    public UserModel(Context context) {
        super(context);
        app_version="2.0.1";
        os_name="Android";
        os_version="5.1";
    }
    public void manualLogin(String phone,String password,String token) {
        String url = Constants.USER_LOGIN;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                UserModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        UserModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("app_version",app_version);
        params.put("phone",phone);
        params.put("os_version",os_version);
        params.put("os_name",os_name);
        params.put("token",token);
        params.put("password",password);
        params.put("area_code","852");
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void checkPassword(String password,String token) {
        String url = Constants.CHECK_PASSWORD;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                UserModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        UserModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("check_token",token);
        params.put("check_password",password);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void insertToken(String phone,String token,String area_code) {
        String url = Constants.USER_LOGIN;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                UserModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        UserModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("app_version",app_version);
        params.put("phone",phone);
        params.put("os_name",os_name);
        params.put("area_code",area_code);
        params.put("os_version",os_version);
        params.put("insertToken",token);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void insertTokenToSchool(String phone,String token,String area_code,String school_domain,String school_db) {
        String url = school_domain+Constants.INSERT_TOKEN_SCHOOL;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                UserModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        UserModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

//        HashMap<String,Object> params=new HashMap<String,Object>();
//        params.put("app_version",app_version);
//        params.put("phone",phone);
//        params.put("os_name",os_name);
//        params.put("area_code",area_code);
//        params.put("os_version",os_version);
//        params.put("insertToken",token);
        url+=school_db+"/"+token+"/"+os_name+"/"+phone+"/"+area_code;
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_GET);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }


}
