package com.twinly.enotices.enoticesapp.activity;

import android.app.DatePickerDialog;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.google.firebase.iid.FirebaseInstanceId;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.ChildrenModel;
import com.twinly.enotices.enoticesapp.model.LeavingApplicationModel;
import com.twinly.enotices.enoticesapp.model.UserModel;
import com.twinly.enotices.enoticesapp.protocol.LEAVING_APPLICATION;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddLeavingApplicationActivity extends AppCompatActivity implements BusinessResponse {

    private ArrayList<String> time_types=new ArrayList<String>();
    private ArrayList<String> leaving_reasons =new ArrayList<String>();
    private Spinner sp_time_start;
    private Spinner sp_time_end;
    private Spinner sp_leaving_reason;
    private EditText edt_leaving_end;
    private EditText edt_leaving_start;
    private EditText edt_leaving_reason;
    private EditText edt_contact_phone;
    private DatePickerDialog datePickerDialog;
    private TextView tv_student_name;
    private TextView tv_student_class;
    private Button btn_confirm;
    private UserModel userModel;

    private LinearLayout ll_parent;

    private Button btn_back;
    private TextView tv_title;

    private LeavingApplicationModel leavingModel;
    private ChildrenModel childrenModel;

    private String school_db;
    private String secret_id;

    private LEAVING_APPLICATION leaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leaving_application);


        school_db=getIntent().getStringExtra("school_db");
        secret_id=getIntent().getStringExtra("secret_id");

        childrenModel =new ChildrenModel(this);
        childrenModel.addResponseListener(this);

        leavingModel=new LeavingApplicationModel(this);
        leavingModel.addResponseListener(this);

        btn_back=findViewById(R.id.btn_header_left);
        tv_title=findViewById(R.id.tv_header_title);
        tv_title.setText("Ask For Leave");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_student_class=findViewById(R.id.tv_leaving_student_class);
        tv_student_name=findViewById(R.id.tv_leaving_student_name);

        sp_time_start=findViewById(R.id.sp_leaving_start);
        sp_time_end=findViewById(R.id.sp_leaving_end);
        sp_leaving_reason=findViewById(R.id.sp_leaving_type);
        edt_leaving_reason=findViewById(R.id.edt_leaving_reason);
        edt_contact_phone=findViewById(R.id.edt_leaving_phone);
        btn_confirm=findViewById(R.id.btn_leaving_confirm);

        ll_parent=findViewById(R.id.ll_leaving_app_parent);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View pop= LayoutInflater.from(AddLeavingApplicationActivity.this).inflate(R.layout.popup_password,null);
                final PopupWindow window=new PopupWindow(pop,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setFocusable(true);
                window.setBackgroundDrawable(new PaintDrawable());
                window.showAtLocation(ll_parent, Gravity.BOTTOM,0,0);

                final EditText edt_pop_pwd=pop.findViewById(R.id.edt_popup_pwd);
                Button btn_pop_confirm=pop.findViewById(R.id.btn_popup_confirm);

                btn_pop_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userModel=new UserModel(AddLeavingApplicationActivity.this);
                        userModel.addResponseListener(AddLeavingApplicationActivity.this);
                        String password=edt_pop_pwd.getText().toString();
                        String token= FirebaseInstanceId.getInstance().getToken();
                        userModel.checkPassword(password,token);
                        window.dismiss();
                    }
                });
            }
        });

        edt_leaving_start=findViewById(R.id.edt_leaving_start);
        edt_leaving_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edt_leaving_start.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                };
                datePickerDialog=new DatePickerDialog(AddLeavingApplicationActivity.this,onDateSetListener,2018,(3-1),10);
                datePickerDialog.show();

            }
        });

        edt_leaving_end=findViewById(R.id.edt_leaving_end);
        edt_leaving_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edt_leaving_end.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                };
                datePickerDialog=new DatePickerDialog(AddLeavingApplicationActivity.this,onDateSetListener,2018,(3-1),10);
                datePickerDialog.show();

            }
        });

        time_types.add("AM");
        time_types.add("PM");

        leaving_reasons.add("Personal Leave");
        leaving_reasons.add("Sick Leave");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,time_types);
        sp_time_start.setAdapter(adapter);
        sp_time_end.setAdapter(adapter);

        ArrayAdapter<String> leaving_reason_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,leaving_reasons);
        sp_leaving_reason.setAdapter(leaving_reason_adapter);

        childrenModel.getStudentInfo(school_db,secret_id);

    }

    private void addApplication(){
        leaving=new LEAVING_APPLICATION();
        leaving.start_time=edt_leaving_start.getText().toString();
        leaving.end_time=edt_leaving_end.getText().toString();
        leaving.start_time_type=sp_time_start.getSelectedItemPosition()+"";
        leaving.end_time_type=sp_time_end.getSelectedItemPosition()+"";
        leaving.leaving_reason=edt_leaving_reason.getText().toString();
        leaving.type=sp_leaving_reason.getSelectedItemPosition()+"";
        String contact_phone=edt_contact_phone.getText().toString();
        leavingModel.addLeavingApplication(school_db,secret_id,contact_phone,leaving);
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if (url.startsWith(BeeQuery.getAbsoluteUrl(Constants.GET_STUDENT_INFO))){
            Log.d("LeavingActivity","[GET_STUDENT_INFO]:"+jo.toString());
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
        if (url.startsWith(BeeQuery.getAbsoluteUrl(Constants.ADD_LEAVING_APPLICATION))){
            Log.d("LeavingActivity","[ADD_LEAVING_APP]:"+jo.toString());
            if (jo!=null){
                if (jo.optInt("code")==0){
                    Toast.makeText(this,jo.optString("msg"),Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(this,jo.optString("msg"),Toast.LENGTH_LONG).show();
                }
            }
        }
        if (url.startsWith(BeeQuery.getAbsoluteUrl(Constants.CHECK_PASSWORD))){
            Log.d("LeavingActivity","[CheckPassword]:"+jo.toString());
            if (jo!=null){
                if (jo.optBoolean("logged")){
                    addApplication();
                }else {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
