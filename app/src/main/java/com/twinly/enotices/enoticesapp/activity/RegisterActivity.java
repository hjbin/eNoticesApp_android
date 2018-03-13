package com.twinly.enotices.enoticesapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.twinly.enotices.enoticesapp.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findView();
    }

    private void findView() {
        this.btn_register=findViewById(R.id.btn_register);
    }
}
