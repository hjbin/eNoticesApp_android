package com.twinly.enotices.enoticesapp.model;

import android.content.Context;

import com.BeeFramework.model.BaseModel;
import com.BeeFramework.model.BeeCallback;
import com.BeeFramework.view.MyProgressDialog;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.protocol.CHILDREN;
import com.twinly.enotices.enoticesapp.protocol.NOTICE;
import com.twinly.enotices.enoticesapp.protocol.NOTICE_QUESTION;
import com.twinly.enotices.enoticesapp.protocol.QA_QUESTION;
import com.twinly.enotices.enoticesapp.protocol.USER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huangjinbin on 2018/2/14.
 */

public class NoticeModel extends BaseModel {
    public NOTICE notice;
    public String reply_id;
    public ArrayList<NOTICE_QUESTION> questionList=new ArrayList<NOTICE_QUESTION>();
    public ArrayList<QA_QUESTION> qa_list=new ArrayList<QA_QUESTION>();
    public NoticeModel(Context context) {
        super(context);
    }



    public void showNoticeContent(String secret_id, String school_db, final String notice_id) {
        String url =Constants.SHOW_NOTICE_CONTENT;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    notice=new NOTICE();
                    notice.fromJson(jo);
                    NoticeModel.this.OnMessageResponse(url, jo, status);
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("notice_id",notice_id);
        params.put("school_db",school_db);
        params.put("secret_id",secret_id);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void getUnread(String secret_id, String school_db, final String notice_id) {
        String url =Constants.SHOW_NOTICE_CONTENT;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    notice=new NOTICE();
                    notice.fromJson(jo);
                    NoticeModel.this.OnMessageResponse(url, jo, status);
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("notice_id",notice_id);
        params.put("school_db",school_db);
        params.put("secret_id",secret_id);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void getNoticeQuestion(String secret_id, String school_db, final String notice_id) {
        String url =Constants.READ_REPLY_OPTION;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    if (jo.optBoolean("logged")){
                        JSONArray ja=jo.optJSONArray("result");
                            for (int i=0;i<ja.length();i++){
                                JSONObject question=ja.optJSONObject(i);
                                reply_id=question.optString("reply_id");
                                NOTICE_QUESTION qItem=new NOTICE_QUESTION();
                                qItem.fromJson(question);
                                questionList.add(qItem);
                            }
                    }
                    NoticeModel.this.OnMessageResponse(url, jo, status);
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("notice_id",notice_id);
        params.put("school_db",school_db);
        params.put("secret_id",secret_id);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void insertReplyAnswer(String school_db,String reply_id,   String option_id,String question_id ) {
        String url =Constants.INSERT_REPLY_ANSWER;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                NoticeModel.this.OnMessageResponse(url, jo, status);

            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("reply_id",reply_id);
        params.put("school_db",school_db);
        params.put("option_id",option_id);
        params.put("question_id",question_id);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void insertReplyAnswerText(String school_db,String reply_id,   String open_text,String question_id ) {
        String url =Constants.INSERT_REPLY_ANSWER_TEXT;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                NoticeModel.this.OnMessageResponse(url, jo, status);

            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("reply_id",reply_id);
        params.put("school_db",school_db);
        params.put("open_text",open_text);
        params.put("question_id",question_id);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void updateReplyAnswer(String school_db,String reply_id) {
        String url =Constants.UPDATE_REPLY_ANSWER;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                NoticeModel.this.OnMessageResponse(url, jo, status);

            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("reply_id",reply_id);
        params.put("school_db",school_db);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void getQAList(String school_db, final String notice_id) {
        String url =Constants.GET_QA_LIST;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                qa_list.clear();
                if (null != jo)
                {
                    if (jo.optInt("code")==0){
                        JSONArray ja=jo.optJSONArray("notice_qa");
                        for (int i=0;i<ja.length();i++){
                            JSONObject question=ja.optJSONObject(i);
                            QA_QUESTION qaItem=new QA_QUESTION();
                            qaItem.fromJson(question);
                            qa_list.add(qaItem);
                        }
                    }
                    NoticeModel.this.OnMessageResponse(url, jo, status);
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("notice_id",notice_id);
        params.put("school_db",school_db);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }

    public void addQAQuestion(String school_db, final String notice_id,String qa_question) {
        String url =Constants.ADD_QA_QUESTION;

        BeeCallback<JSONObject> cb = new BeeCallback<JSONObject>(){

            @Override
            public void callback(String url, JSONObject jo, AjaxStatus status) throws JSONException {
                NoticeModel.this.callback(url, jo, status);
                if (null != jo)
                {
                    NoticeModel.this.OnMessageResponse(url, jo, status);
                }
            }
        };

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("notice_id",notice_id);
        params.put("school_db",school_db);
        params.put("qa_question",qa_question);
        cb.url(url).type(JSONObject.class).method(com.external.androidquery.util.Constants.METHOD_POST).params(params);
        MyProgressDialog mPro = new MyProgressDialog(mContext, "请稍后...");
        aq.progress(mPro.mDialog).ajax(cb);
    }



}
