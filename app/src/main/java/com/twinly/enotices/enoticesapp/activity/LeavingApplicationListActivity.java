package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.LeavingApplicationPagerAdapter;
import com.twinly.enotices.enoticesapp.adapter.OnWithdrawListener;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.LeavingApplicationModel;

import org.json.JSONException;
import org.json.JSONObject;

public class LeavingApplicationListActivity extends AppCompatActivity implements BusinessResponse,OnWithdrawListener{

    private LeavingApplicationModel leavingApplicationModel;
    private LeavingApplicationPagerAdapter mAdapter;
    private TabLayout tabLayout;
    private ViewPager vp_leaving;
    private Button btn_left;
    private Button btn_plus;
    private TextView tv_title;
    private String school_db;
    private String secret_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaving_application_list);

        school_db=getIntent().getStringExtra("school_db");
        secret_id=getIntent().getStringExtra("secret_id");

        tv_title=findViewById(R.id.tv_header_title);
        tv_title.setText("Ask For Leave");

        btn_left=findViewById(R.id.btn_header_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_plus=findViewById(R.id.btn_header_right);
        btn_plus.setVisibility(View.VISIBLE);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(LeavingApplicationListActivity.this,AddLeavingApplicationActivity.class);
                it.putExtra("school_db",school_db);
                it.putExtra("secret_id",secret_id);
                startActivity(it);
            }
        });

        tabLayout=findViewById(R.id.tab_group_leaving);
        vp_leaving=findViewById(R.id.vp_leaving);
        leavingApplicationModel=new LeavingApplicationModel(this);
        leavingApplicationModel.addResponseListener(this);
        leavingApplicationModel.getLeavingApplicationList(school_db,secret_id);




    }

    @Override
    protected void onResume() {

        super.onResume();
        leavingApplicationModel.getLeavingApplicationList(school_db,secret_id);
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if (url.startsWith(BeeQuery.getAbsoluteUrl(Constants.GET_LEAVING_APPLICATION_LIST))){
            Log.d("LeavingListActivity","[GET_LEAVING_LIST]:"+jo.toString());
            if (jo!=null){
                if (jo.optInt("code")==0){
                    if (mAdapter==null){
                        mAdapter=new LeavingApplicationPagerAdapter(getFragmentManager(),leavingApplicationModel.pendingList,leavingApplicationModel.historyList);
                        vp_leaving.setAdapter(mAdapter);

                        vp_leaving.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                tabLayout.getTabAt(position).select();
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                vp_leaving.setCurrentItem(tab.getPosition());
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });

                    }else {
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    @Override
    public void onWithdraw(String app_id) {

    }
}
