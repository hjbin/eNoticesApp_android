package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.twinly.enotices.enoticesapp.R;

public class LoginActivity extends AppCompatActivity {


    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        findView();
        setOnClick();
    }

    private void setOnClick() {
        this.btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(LoginActivity.this,ChildrenListActivity.class);
                startActivity(it);
            }
        });
    }

    private void findView(){
        btn_login=(Button)findViewById(R.id.btn_login);
    }
}
