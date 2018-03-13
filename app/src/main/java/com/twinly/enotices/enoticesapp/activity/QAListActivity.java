package com.twinly.enotices.enoticesapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.QAListAdapter;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.NoticeModel;

import org.json.JSONException;
import org.json.JSONObject;

public class QAListActivity extends AppCompatActivity implements BusinessResponse{

    private String notice_id;
    private String school_db;
    private NoticeModel noticeModel;

    private EditText edt_qa_question;
    private Button btn_confirm;
    private Button btn_back;
    private TextView tv_title;
    private ListView lv_qa_list;

    private QAListAdapter qaListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qalist);

        notice_id=getIntent().getStringExtra("notice_id");
        school_db=getIntent().getStringExtra("school_db");

        noticeModel=new NoticeModel(this);
        noticeModel.addResponseListener(this);

        findView();
        setAction();
    }

    private void setAction() {
        tv_title.setText("Q&A");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qa_question=edt_qa_question.getText().toString();
                noticeModel.addQAQuestion(school_db,notice_id,qa_question);
            }
        });

        noticeModel.getQAList(school_db,notice_id);
    }

    private void findView() {
        edt_qa_question=findViewById(R.id.edt_qa_question);
        edt_qa_question.clearFocus();
        btn_confirm=findViewById(R.id.btn_qa_submit);

        tv_title=findViewById(R.id.tv_header_title);
        btn_back=findViewById(R.id.btn_header_left);
        lv_qa_list=findViewById(R.id.lv_qa_list);

    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.GET_QA_LIST))){
            Log.i("[QAListActivity]","[GET_QA_LIST]: "+jo.toString()+"list size: "+noticeModel.qa_list.size());
            if (jo!=null){
                if (qaListAdapter==null){
                    qaListAdapter=new QAListAdapter(this,noticeModel.qa_list);
                    lv_qa_list.setAdapter(qaListAdapter);
                }else {
                    qaListAdapter.notifyDataSetChanged();
                }
            }
        }
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.ADD_QA_QUESTION))){
            Log.i("[QAListActivity]","[ADD_QA_QUESTION]: "+jo.toString());
            if (jo!=null){
                if (jo.optInt("code")==0){
                    Toast.makeText(this,jo.optString("msg"),Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(this,jo.optString("msg"),Toast.LENGTH_LONG).show();
                }
            }else {

                Toast.makeText(this,"Network Error!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
