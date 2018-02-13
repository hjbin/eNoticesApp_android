package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.ChildrenListAdapter;

public class ChildrenListActivity extends AppCompatActivity {

    private ListView lv_children_list;
    private ChildrenListAdapter adapter;
    private Button back;
    private Button btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_list);
        findView();
        setAction();
    }

    private void setAction() {
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ChildrenListActivity.this,SettingActivity.class);
                startActivity(it);
            }
        });

        adapter=new ChildrenListAdapter(this);
        this.lv_children_list.setAdapter(adapter);
        this.lv_children_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(ChildrenListActivity.this,ChildrenNoticeActivity.class);
                startActivity(it);
            }
        });
    }

    private void findView() {
        this.lv_children_list=findViewById(R.id.lv_children_list);
        this.btn_setting=findViewById(R.id.btn_system_setting);
    }
}
