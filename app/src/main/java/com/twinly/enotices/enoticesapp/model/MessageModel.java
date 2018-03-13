package com.twinly.enotices.enoticesapp.model;


import android.content.Context;

import com.BeeFramework.model.BaseModel;
import com.BeeFramework.model.BeeCallback;
import com.BeeFramework.view.MyProgressDialog;
import com.external.androidquery.callback.AjaxStatus;
import com.external.stomp.Message;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.MESSAGE;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jbhuang on 2/13/2018.
 */

public class MessageModel extends BaseModel{

    public ArrayList<MESSAGE> messageList=new ArrayList<>();
    public MessageModel(Context ctx){
        super(ctx);
    }

    public void getUnreadMessage(String secret_id) {
        String url = Constants.SHOW_UNREAD_MESSAGE;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status)
            {
                MessageModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    messageList.clear();
                    try
                    {
                        JSONArray messageArray = jo.optJSONArray("result");
                        for (int i = 0; i < messageArray.length(); i++)
                        {
                            JSONObject jsonItem = messageArray.getJSONObject(i);
                            MESSAGE msgItem =new MESSAGE();
                            msgItem.fromJson(jsonItem);
                            messageList.add(msgItem);
                        }

                        MessageModel.this.OnMessageResponse(url, jo, status);
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
        params.put("school_db","testschool_8_9");
        params.put("filter","");
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }


}
