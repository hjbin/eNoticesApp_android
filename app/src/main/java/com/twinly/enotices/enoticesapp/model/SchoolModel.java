package com.twinly.enotices.enoticesapp.model;

import android.content.Context;

import com.BeeFramework.model.BaseModel;
import com.BeeFramework.model.BeeCallback;
import com.BeeFramework.view.MyProgressDialog;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.SCHOOL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by huangjinbin on 2018/3/13.
 */

public class SchoolModel extends BaseModel{
    public SCHOOL school;

    public SchoolModel(Context context) {
        super(context);
    }

    public void getServices(String school_db) {
        String url = Constants.GET_SERVICES_LIST;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                SchoolModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    try
                    {
                        if (jo.optInt("code")==0){
                            SCHOOL item=new SCHOOL();
                            item.fromJson(jo.optJSONObject("services"));
                            school=item;
                        }
                        SchoolModel.this.OnMessageResponse(url, jo, status);
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
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }
}
