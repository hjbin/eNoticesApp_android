package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.ChildrenModel;
import com.twinly.enotices.enoticesapp.utils.device.DeviceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class TemperatureReportActivity extends AppCompatActivity implements BusinessResponse{

    private Button btn_back;
    private TextView tv_title;
    private Button btn_submit;
    private EditText edt_temperature;
    private TextView tv_student_name;
    private TextView tv_student_class;
    private ChildrenModel childrenModel;
    private String school_db;
    private String secret_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_report);

        Intent it= getIntent();
        school_db=it.getStringExtra("school_db");
        secret_id=it.getStringExtra("secret_id");


        findView();
        setOnClick();

        childrenModel=new ChildrenModel(this);
        childrenModel.addResponseListener(this);
        childrenModel.getStudentInfo(school_db,secret_id);

    }

    private void setOnClick() {
        tv_title.setText("Temperature Report");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temperature=edt_temperature.getText().toString();
                if (temperature!=null){
                    if (!temperature.equals("")){
                        Date curDate=new Date();
                        String date= DeviceUtils.getStringByFormat(curDate,"yyyy-MM-dd");
                        childrenModel.addTemperatureRecord(school_db,secret_id,temperature,date);
                    }else {
                        Toast.makeText(TemperatureReportActivity.this,"Temperature cannot be empty!",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(TemperatureReportActivity.this,"Temperature cannot be empty!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void findView() {

        btn_back=findViewById(R.id.btn_header_left);
        tv_title=findViewById(R.id.tv_header_title);
        btn_submit=findViewById(R.id.btn_temperature_submit);
        edt_temperature=findViewById(R.id.edt_temperature);
        tv_student_name=findViewById(R.id.tv_student_name);
        tv_student_class=findViewById(R.id.tv_student_class);
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if (url.startsWith(BeeQuery.getAbsoluteUrl(Constants.GET_STUDENT_INFO))){
            Log.d("TemperatureActivity","[GET_STUDENT_INFO]:"+jo.toString());
            if (jo!=null){
                if (jo.optInt("code")==0){
                    JSONObject student_info=jo.optJSONObject("student_info");
                    String student_name=student_info.optString("student_name");
                    String student_class=student_info.optString("class");
                    tv_student_name.setText(student_name);
                    tv_student_class.setText(student_class);
                }
            }
        }
        if (url.startsWith(BeeQuery.getAbsoluteUrl(Constants.ADD_TEMPERATURE_RECORD))){
            Log.d("TemperatureActivity","[ADD_TEMP_RECORD]:"+jo.toString());
            if (jo!=null){
                if (jo.optInt("code")==0){
                    String msg=jo.optString("msg");
                    Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    String msg=jo.optString("msg");
                    Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
