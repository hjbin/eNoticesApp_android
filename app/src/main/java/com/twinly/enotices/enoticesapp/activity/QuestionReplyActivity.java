package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.OnAnswerListener;
import com.twinly.enotices.enoticesapp.adapter.QuestionListAdapter;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.NoticeModel;
import com.twinly.enotices.enoticesapp.protocol.NOTICE_QUESTION;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionReplyActivity extends AppCompatActivity implements BusinessResponse,OnAnswerListener{

    private ListView lv_question;
    private NoticeModel noticeModel;
    private String school_db;
    private String notice_id;
    private String secret_id;
    private Button btn_submit;

    private QuestionListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_reply);

        Intent it=getIntent();
        school_db=it.getStringExtra("school_db");
        notice_id=it.getStringExtra("notice_id");
        secret_id=it.getStringExtra("secret_id");

        btn_submit=findViewById(R.id.btn_notice_question_submit);
        setOnClick();

        lv_question=findViewById(R.id.lv_question);
        noticeModel=new NoticeModel(this);
        noticeModel.addResponseListener(this);
        noticeModel.getNoticeQuestion(secret_id,school_db,notice_id);
        

    }

    private void setOnClick() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<noticeModel.questionList.size();i++){
                    NOTICE_QUESTION question=noticeModel.questionList.get(i);
                    String log="";
                    log+="[question type]: "+question.ans_type;
                    log+=" | [qid]: "+question.question_id;
                    log+=" | [reply_id]: "+question.reply_id;
                    if (question.ans_type.equals("O")){
                        noticeModel.insertReplyAnswerText(school_db,question.reply_id,question.answer_text,question.question_id);
                        log+=" | [answer_text]: "+question.answer_text;
                    }else if (question.ans_type.equals("S")){
                        noticeModel.insertReplyAnswer(school_db,question.reply_id,question.answer_single,question.question_id);
                        log+=" | [answer_option_id]: "+question.answer_single;
                    }else {
                        for (int k=0;k<question.answer_multiple.size();k++){
                            if (question.answer_multiple.get(k)){
                                noticeModel.insertReplyAnswer(school_db,question.reply_id,question.option_id.get(k),question.question_id);
                                log+=" | [answer_option_id]: "+question.option_id.get(k);
                            }
                        }
                    }


                    Log.d("[QuestionReplyActivity]","[Answers]: "+log);
                }

                noticeModel.updateReplyAnswer(school_db,noticeModel.reply_id);
            }
        });
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {

        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.READ_REPLY_OPTION))){
            Log.i("[QuestionReplyActivity]","[read_reply_option]: "+jo.toString());

            if (mAdapter==null){
                mAdapter=new QuestionListAdapter(this,noticeModel.questionList);
                mAdapter.setOnAnswerListener(this);
                lv_question.setAdapter(mAdapter);
            }else {
                mAdapter.notifyDataSetChanged();
            }
        }

        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.INSERT_REPLY_ANSWER))){
            Log.i("[QuestionReplyActivity]","[insert_reply_answer]: "+jo.toString());
        }
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.INSERT_REPLY_ANSWER_TEXT))){
            Log.i("[QuestionReplyActivity]","[insert_reply_answer_text]: "+jo.toString());
        }
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.UPDATE_REPLY_ANSWER))){
            Log.i("[QuestionReplyActivity]","[update_reply_answer]: "+jo.toString());
            if (jo!=null){
                if (jo.optBoolean("logged")){
                    Toast.makeText(this,"签署成功",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }

    }

    @Override
    public void onAnswerOpen(int question_pos, String answer) {
        noticeModel.questionList.get(question_pos).answer_text=answer;
    }

    @Override
    public void onAnswerSingle(int question_pos, int option_pos) {
        noticeModel.questionList.get(question_pos).answer_single=noticeModel.questionList.get(question_pos).option_id.get(option_pos);
    }

    @Override
    public void onAnswerMultiple(int question_pos, int option_pos, boolean isChecked) {
        noticeModel.questionList.get(question_pos).answer_multiple.set(option_pos,isChecked);
    }
}
