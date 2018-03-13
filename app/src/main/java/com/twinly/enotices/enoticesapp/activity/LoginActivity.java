package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements BusinessResponse{


    private Button btn_login;
    private Button btn_register;
    private Button btn_forget_pwd;
    private EditText edt_account;
    private EditText edt_pwd;
    private UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        findView();
        setOnClick();

        userModel=new UserModel(this);
        userModel.addResponseListener(this);

    }

    private void setOnClick() {
        this.btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it=new Intent(LoginActivity.this,ChildrenListActivity.class);
//                startActivity(it);
                if (checkEdt()){
                    String phone=edt_account.getText().toString();
                    String pwd=edt_pwd.getText().toString();
                    String token=FirebaseInstanceId.getInstance().getToken();
                    userModel.manualLogin(phone,pwd,token);

                }
            }
        });

        this.btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(it);
            }
        });
    }

    private boolean checkEdt() {
        if (!this.edt_account.getText().toString().isEmpty() && !this.edt_pwd.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }

    private void findView(){
        edt_account=findViewById(R.id.edt_phone_num_login);
        edt_pwd=findViewById(R.id.edt_pwd_login);

        btn_login=(Button)findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);
        btn_forget_pwd=findViewById(R.id.btn_forget_pwd);
        btn_forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if (url.startsWith(BeeQuery.getAbsoluteUrl(Constants.USER_LOGIN))){
            Log.d("LoginActivity","[USER LOGIN]:"+jo.toString());
            if (jo!=null){
                if (jo.optInt("code")==200){
                    Intent it=new Intent(LoginActivity.this,ChildrenListActivity.class);
                    startActivity(it);
                }
            }
        }

    }
}
