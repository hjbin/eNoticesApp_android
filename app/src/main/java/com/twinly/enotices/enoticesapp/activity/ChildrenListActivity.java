package com.twinly.enotices.enoticesapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.google.firebase.iid.FirebaseInstanceId;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.ChildrenListAdapter;
import com.twinly.enotices.enoticesapp.adapter.OnPushStateChangeListener;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.ChildrenModel;
import com.twinly.enotices.enoticesapp.model.UserModel;
import com.twinly.enotices.enoticesapp.protocol.CHILDREN;
import com.twinly.enotices.enoticesapp.protocol.USER;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChildrenListActivity extends AppCompatActivity implements BusinessResponse,OnPushStateChangeListener{

    private ListView lv_children_list;
    private ChildrenListAdapter adapter;
    private Button back;
    private Button btn_setting;
    private ChildrenModel childrenModel;
    private UserModel userModel;
    private USER user;
    private ArrayList<CHILDREN> childrenList=new ArrayList<CHILDREN>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_list);
        childrenModel=new ChildrenModel(this);
        childrenModel.addResponseListener(this);
        findView();
        setAction();
        user=new USER();
        user.Username="53272861";
        user.area_code="852";
        userModel=new UserModel(this);
        userModel.addResponseListener(this);
        childrenModel.getBindChild(user);

    }

    private void setAction() {
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ChildrenListActivity.this,SettingActivity.class);
                startActivity(it);
            }
        });
        this.lv_children_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(ChildrenListActivity.this,ChildrenNoticeActivity.class);
                Log.i("[ChildrenListActivity]","secret_id:"+childrenList.get(i).secret_id);
                it.putExtra("secret_id",childrenList.get(i).secret_id);
                it.putExtra("school_db",childrenList.get(i).school_db);
                startActivity(it);
            }
        });
    }

    private void findView() {
        this.lv_children_list=findViewById(R.id.lv_children_list);
        this.btn_setting=findViewById(R.id.btn_system_setting);
    }

    private void getBindChild(USER user,ArrayList<CHILDREN> childList){
        String token= FirebaseInstanceId.getInstance().getToken();
        for (int i=0;i<childList.size();i++){

            this.userModel.insertTokenToSchool(user.Username,token,user.area_code,childList.get(i).school_domain,childList.get(i).school_db);
            this.childrenModel.showBindChild(user,childList.get(i));
        }
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.GET_BIND_CHILD))){
            Log.i("[ChildrenListActivity]","[get_bind_child]: "+jo.toString());
            getBindChild(user,childrenModel.childrenList);
        }
        if(url.contains(Constants.SHOW_BIND_CHILD)){
            Log.i("[ChildrenListActivity]","[show_bind_child]: "+jo.toString());
            CHILDREN children=new CHILDREN();
            children.fromJsonSchool(jo.optJSONObject("result"));
            childrenList.add(children);

            if (adapter==null){
                adapter=new ChildrenListAdapter(this,childrenList);
                adapter.setOnPushStateChangedListener(this);
                lv_children_list.setAdapter(adapter);
            }else {
                adapter.notifyDataSetChanged();
            }
        }
        if (url.contains(Constants.INSERT_TOKEN_SCHOOL)){
            Log.i("ChildrenListActivity","[INSERT TOKEN SCHOOL]:"+jo.toString());
        }
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.CHANGE_PUSH_STATE))){
            Log.i("[ChildrenListActivity]","[change_push_state]: "+jo.toString());
            if (jo.optBoolean("logged")){
                Toast.makeText(this,"Push state changed successfully",Toast.LENGTH_LONG).show();
            }else {

                Toast.makeText(this,"Push state changed failed",Toast.LENGTH_LONG).show();
                childrenModel.getBindChild(user);
            }
        }
    }

    @Override
    public void onPushStateChange(CHILDREN children, boolean isPushed) {
        String token=FirebaseInstanceId.getInstance().getToken();
        String pushState="Y";
        if (!isPushed){
            pushState="N";
        }
        childrenModel.changePushState(children.school_db,children.secret_id,token,pushState);
    }
}
