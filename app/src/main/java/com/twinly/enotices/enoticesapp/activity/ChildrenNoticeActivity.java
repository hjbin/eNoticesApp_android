package com.twinly.enotices.enoticesapp.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.fragment.AnnouncementFragment;
import com.twinly.enotices.enoticesapp.fragment.MoreFragment;
import com.twinly.enotices.enoticesapp.fragment.NoticeFragment;
import com.twinly.enotices.enoticesapp.fragment.PendingFragment;
import com.twinly.enotices.enoticesapp.utils.device.DeviceUtils;

public class ChildrenNoticeActivity extends AppCompatActivity {

    private RadioButton rbtn_pending;

    private RadioButton rbtn_notice;
    private RadioButton rbtn_event;
    private RadioButton rbtn_announ;
    private  RadioButton rbtn_more;

    private ImageView iv_school_info;

    private String secret_id;
    private String school_db;

    private RadioGroup rg_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_notice);
        secret_id=getIntent().getStringExtra("secret_id");
        Log.i("[ChildrenNtActivity]","secret_id:"+secret_id);
        school_db=getIntent().getStringExtra("school_db");
        initView();
        setOnClick();

    }

    private void setOnClick() {
        rg_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbtn_notice:
                        FragmentManager manager=getFragmentManager();
                        AnnouncementFragment noticeFragment=AnnouncementFragment.newInstance(school_db,secret_id,"S");
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.replace(R.id.frame_fragments,noticeFragment,"notice_fragment");
                        transaction.commit();
                        break;
                    case R.id.rbtn_pending:
                        FragmentManager manager2=getFragmentManager();
                        PendingFragment pendingFragment=PendingFragment.newInstance(secret_id,school_db);
                        FragmentTransaction transaction2=manager2.beginTransaction();
                        transaction2.replace(R.id.frame_fragments,pendingFragment,"notice_fragment");
                        transaction2.commit();
                        break;

                    case R.id.rbtn_announ:
                        FragmentManager manager3=getFragmentManager();
                        AnnouncementFragment announcementFragment=AnnouncementFragment.newInstance(school_db,secret_id,"S");
                        FragmentTransaction transaction3=manager3.beginTransaction();
                        transaction3.replace(R.id.frame_fragments,announcementFragment,"notice_fragment");
                        transaction3.commit();
                        break;

                    case R.id.rbtn_event:
                        FragmentManager manager5=getFragmentManager();
                        AnnouncementFragment announcementFragment1=AnnouncementFragment.newInstance(school_db,secret_id,"E");
                        FragmentTransaction transaction5=manager5.beginTransaction();
                        transaction5.replace(R.id.frame_fragments,announcementFragment1,"notice_fragment");
                        transaction5.commit();
                        break;

                    case R.id.rbtn_more:
                        FragmentManager manager4=getFragmentManager();
                        MoreFragment moreFragment=MoreFragment.newInstance("","");
                        FragmentTransaction transaction4=manager4.beginTransaction();
                        transaction4.replace(R.id.frame_fragments,moreFragment,"notice_fragment");
                        transaction4.commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initView() {
        rbtn_pending=(RadioButton)findViewById(R.id.rbtn_pending);
        rbtn_notice=(RadioButton)findViewById(R.id.rbtn_notice);
        rbtn_event=(RadioButton)findViewById(R.id.rbtn_event);
        rbtn_announ=(RadioButton)findViewById(R.id.rbtn_announ);
        rbtn_more=(RadioButton)findViewById(R.id.rbtn_more);
        rg_bottom=(RadioGroup)findViewById(R.id.rg_bottom);
        iv_school_info=findViewById(R.id.iv_school_information);

        iv_school_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ChildrenNoticeActivity.this,SchoolInfoActivity.class);
                it.putExtra("school_db",school_db);
                it.putExtra("secret_id",secret_id);
                startActivity(it);
            }
        });

        FragmentManager manager2=getFragmentManager();
        PendingFragment pendingFragment=PendingFragment.newInstance(secret_id,school_db);
        FragmentTransaction transaction2=manager2.beginTransaction();
        transaction2.replace(R.id.frame_fragments,pendingFragment,"notice_fragment");
        transaction2.commit();

        int length= DeviceUtils.dip2px(this,30);
        Drawable image=getResources().getDrawable(R.drawable.selector_pending);
        image.setBounds(0,0,length,length);
        rbtn_pending.setCompoundDrawables(null,image,null,null);

        Drawable image_notice=getResources().getDrawable(R.drawable.selector_notice);
        image_notice.setBounds(0,0,length,length);
        rbtn_notice.setCompoundDrawables(null,image_notice,null,null);

        Drawable image_event=getResources().getDrawable(R.drawable.selector_event);
        image_event.setBounds(0,0,length,length);
        rbtn_event.setCompoundDrawables(null,image_event,null,null);

        Drawable image_announ=getResources().getDrawable(R.drawable.selector_announcement);
        image_announ.setBounds(0,0,length,length);
        rbtn_announ.setCompoundDrawables(null,image_announ,null,null);

        Drawable image_more=getResources().getDrawable(R.drawable.selector_more);
        image_more.setBounds(0,0,length,length);
        rbtn_more.setCompoundDrawables(null,image_more,null,null);
    }
}
