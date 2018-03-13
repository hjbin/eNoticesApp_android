package com.twinly.enotices.enoticesapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.twinly.enotices.enoticesapp.R;

public class WebBrowserActivity extends AppCompatActivity {

    String WebUrl;
    private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        WebUrl=getIntent().getStringExtra("url");

        web=findViewById(R.id.web_browser);
        web.loadUrl(WebUrl);

    }
}
