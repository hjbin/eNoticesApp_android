package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.external.imagezoom.easing.Linear;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.constants.Constants;

public class SettingActivity extends AppCompatActivity {

    private LinearLayout ll_about_us;
    private LinearLayout ll_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ll_about_us=findViewById(R.id.ll_about_us);
        ll_privacy=findViewById(R.id.ll_privacy_policy);

        ll_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String about_us= Constants.ABOUT_US;
                Intent it=new Intent(SettingActivity.this,WebBrowserActivity.class);
                it.putExtra("url",about_us);
                startActivity(it);
            }
        });

        ll_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String privacy_policy= Constants.PRIVACY_POLICY;
                Intent it=new Intent(SettingActivity.this,WebBrowserActivity.class);
                it.putExtra("url",privacy_policy);
                startActivity(it);
            }
        });

    }
}
