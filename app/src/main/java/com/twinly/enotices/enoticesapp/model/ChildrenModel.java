package com.twinly.enotices.enoticesapp.model;

import android.content.Context;

import com.BeeFramework.model.BaseModel;
import com.BeeFramework.model.BeeCallback;
import com.BeeFramework.view.MyProgressDialog;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.CHILDREN;
import com.twinly.enotices.enoticesapp.protocol.MESSAGE;
import com.twinly.enotices.enoticesapp.protocol.USER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huangjinbin on 2018/2/14.
 */

public class ChildrenModel extends BaseModel {

    public ArrayList<CHILDREN> childrenList=new ArrayList<CHILDREN>();

    public ChildrenModel(Context context) {
        super(context);
    }

    public void getBindChild(USER user) {
        String url = Constants.GET_BIND_CHILD;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                ChildrenModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    childrenList.clear();
                    try
                    {
                        if (jo.optBoolean("logged")!=false){
                            JSONArray childrenArray = jo.optJSONArray("result");
                            for (int i = 0; i < childrenArray.length(); i++)
                            {
                                JSONObject jsonItem = childrenArray.getJSONObject(i);
                                CHILDREN childItem =new CHILDREN();
                                childItem.fromJson(jsonItem);
                                childrenList.add(childItem);
                            }
                        }

                        ChildrenModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("parent_phone",user.Username);
        params.put("area_code",user.area_code);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void showBindChild(USER user,CHILDREN child) {
        String url = child.school_domain+Constants.SHOW_BIND_CHILD;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                ChildrenModel.this.callback(url, jo, status);
                if (null != jo)
                {


                    ChildrenModel.this.OnMessageResponse(url, jo, status);
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("phone",user.Username);
        params.put("area_code",user.area_code);
        params.put("school_id",child.school_id);
        params.put("school_db",child.school_db);
        params.put("secret_id",child.secret_id);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void getStudentInfo(String school_db,String secret_id) {
        String url = Constants.GET_STUDENT_INFO;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                ChildrenModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        ChildrenModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("school_db",school_db);
        params.put("secret_id",secret_id);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void addTemperatureRecord(String school_db,String secret_id,String temperature,String date) {
        String url = Constants.ADD_TEMPERATURE_RECORD;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                ChildrenModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        ChildrenModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("school_db",school_db);
        params.put("secret_id",secret_id);
        params.put("temperature",temperature);
        params.put("date",date);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void changePushState(String school_db,String secret_id,String token,String alertState) {
        String url = Constants.CHANGE_PUSH_STATE;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                ChildrenModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        ChildrenModel.this.OnMessageResponse(url, jo, status);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("school_db",school_db);
        params.put("secret_id",secret_id);
        params.put("push_alert",alertState);
        params.put("token",token);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }
}
