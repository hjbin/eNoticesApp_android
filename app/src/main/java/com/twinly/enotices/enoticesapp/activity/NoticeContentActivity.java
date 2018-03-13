package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.external.imagezoom.easing.Linear;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.QuestionListAdapter;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.NoticeModel;
import com.twinly.enotices.enoticesapp.utils.device.DeviceUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class NoticeContentActivity extends AppCompatActivity implements BusinessResponse{

    private NoticeModel noticeModel;
    private String school_db;
    private String secret_id;
    private String notice_id;

    private TextView tv_title;
    private TextView tv_post_date;
    private TextView tv_deadline;
    private WebView wb_content;
    private Button btn_reply;
    private Button btn_qa_list;
    private Button btn_tools;
    private LinearLayout ll_parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);
        school_db=getIntent().getStringExtra("school_db");
        secret_id=getIntent().getStringExtra("secret_id");
        notice_id=getIntent().getStringExtra("notice_id");


        findView();
        setOnClick();

        noticeModel=new NoticeModel(this);
        noticeModel.addResponseListener(this);
        noticeModel.showNoticeContent(secret_id,school_db,notice_id);
    }

    private void setOnClick() {
        btn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(NoticeContentActivity.this, QuestionReplyActivity.class);
                it.putExtra("school_db",school_db);
                it.putExtra("secret_id",secret_id);
                it.putExtra("notice_id",notice_id);
                startActivity(it);
            }
        });
        btn_qa_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(NoticeContentActivity.this, QAListActivity.class);
                it.putExtra("school_db",school_db);
                it.putExtra("secret_id",secret_id);
                it.putExtra("notice_id",notice_id);
                startActivity(it);
            }
        });
        btn_tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("[NoticeContentActivity]","[click] popup click");
                final View popupView= LayoutInflater.from(NoticeContentActivity.this).inflate(R.layout.popup_notice_tools,null);
                final PopupWindow popupWindow=new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new PaintDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(ll_parent, Gravity.BOTTOM,0,0);

                LinearLayout ll_pdf=popupView.findViewById(R.id.ll_pdf);
                ll_pdf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DeviceUtils.openBrowser(NoticeContentActivity.this,"https://credential.top/html/"+noticeModel.notice.pdf_name);
                        popupWindow.dismiss();
                    }
                });

            }
        });
    }

    private void findView() {
        tv_title=findViewById(R.id.tv_detail_title);
        tv_post_date=findViewById(R.id.tv_detail_postdate);
        tv_deadline=findViewById(R.id.tv_detail_deadline);
        wb_content=findViewById(R.id.wb_content);
        btn_reply=findViewById(R.id.btn_reply);
        btn_qa_list=findViewById(R.id.btn_qa_list);
        btn_tools=findViewById(R.id.btn_tool);
        ll_parent=findViewById(R.id.ll_notice_content_parent);

    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.SHOW_NOTICE_CONTENT))){
            Log.i("[NoticeContentActivity]","[show_notice_content]: "+jo.toString());
            tv_title.setText(noticeModel.notice.notice_title);
            tv_post_date.setText(noticeModel.notice.post_date);
            tv_deadline.setText(noticeModel.notice.deadline);
            wb_content.loadUrl("https://credential.top"+noticeModel.notice.notice_content);
        }
    }
}
