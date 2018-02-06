package com.twinly.enotices.enoticesapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.ChildrenListAdapter;

public class ChildrenListActivity extends AppCompatActivity {

    private ListView lv_children_list;
    private ChildrenListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_list);
        findView();
        setAction();
    }

    private void setAction() {
        adapter=new ChildrenListAdapter(this);
        this.lv_children_list.setAdapter(adapter);
    }

    private void findView() {
        this.lv_children_list=findViewById(R.id.lv_children_list);
    }
}
