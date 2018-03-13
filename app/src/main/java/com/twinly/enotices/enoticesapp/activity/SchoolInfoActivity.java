package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.external.imagezoom.easing.Linear;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.QAListAdapter;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.SchoolModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SchoolInfoActivity extends AppCompatActivity implements BusinessResponse{

    private TextView tv_title;
    private Button btn_back;
    private LinearLayout ll_temperature_report;
    private LinearLayout ll_leaving_app;
    private LinearLayout ll_school_bus;
    private String school_db;
    private String secret_id;
    private SchoolModel schoolModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);

        Intent it=getIntent();
        school_db=it.getStringExtra("school_db");
        secret_id=it.getStringExtra("secret_id");

        findView();
        setAction();

        schoolModel=new SchoolModel(this);
        schoolModel.addResponseListener(this);
        schoolModel.getServices(school_db);
    }

    private void setAction() {

        tv_title.setText("More Services");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ll_temperature_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(SchoolInfoActivity.this,TemperatureReportActivity.class);
                it.putExtra("school_db",school_db);
                it.putExtra("secret_id",secret_id);
                startActivity(it);
            }
        });
        ll_leaving_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(SchoolInfoActivity.this,LeavingApplicationListActivity.class);
                it.putExtra("school_db",school_db);
                it.putExtra("secret_id",secret_id);
                startActivity(it);
            }
        });
    }

    private void findView() {
        tv_title=findViewById(R.id.tv_header_title);
        btn_back=findViewById(R.id.btn_header_left);
        ll_temperature_report=findViewById(R.id.ll_temp_report);
        ll_leaving_app=findViewById(R.id.ll_ask_for_leave);
        ll_school_bus=findViewById(R.id.ll_school_bus);
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.GET_SERVICES_LIST))){
            Log.d("[SchoolInfoActivity]","[get_services_list]"+jo.toString());
            if (jo!=null){
                if (jo.optInt("code")==0){
                    if (schoolModel.school.temperature_report.equals("0")){
                        ll_temperature_report.setVisibility(View.GONE);
                    }else{
                        ll_temperature_report.setVisibility(View.VISIBLE);
                    }
                    if (schoolModel.school.leaving_application.equals("0")){
                        ll_leaving_app.setVisibility(View.GONE);
                    }else{
                        ll_leaving_app.setVisibility(View.VISIBLE);
                    }
                    if (schoolModel.school.school_bus.equals("0")){
                        ll_school_bus.setVisibility(View.GONE);
                    }else {
                        ll_school_bus.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}
