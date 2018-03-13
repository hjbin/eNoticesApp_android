package com.twinly.enotices.enoticesapp.model;

import android.content.Context;

import com.BeeFramework.model.BaseModel;
import com.BeeFramework.model.BeeCallback;
import com.BeeFramework.view.MyProgressDialog;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.ACTIVITY;
import com.twinly.enotices.enoticesapp.protocol.MESSAGE;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huangjinbin on 2018/3/7.
 */

public class ActivityModel extends BaseModel {
    public ArrayList<ACTIVITY> actList=new ArrayList<ACTIVITY>();

    public ActivityModel(Context context) {
        super(context);
    }

    public void getActivityList(String school_db,String secret_id,String filter,String type) {
        String url = Constants.SHOW_UNREAD_ACTIVITY;

        final String event_type=type;
        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                ActivityModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    actList.clear();
                    try
                    {
                        JSONArray messageArray = jo.optJSONArray("result");
                        for (int i = 0; i < messageArray.length(); i++)
                        {
                            JSONObject jsonItem = messageArray.getJSONObject(i);
                            ACTIVITY actItem =new ACTIVITY();
                            actItem.fromJson(jsonItem);
                            actItem.event_type=event_type;
                            actList.add(actItem);
                        }

                        ActivityModel.this.OnMessageResponse(url, jo, status);
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
        params.put("filter",filter);
        params.put("type",type);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

}
