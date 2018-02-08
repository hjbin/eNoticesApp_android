package com.twinly.enotices.enoticesapp.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.fragment.AnnouncementFragment;
import com.twinly.enotices.enoticesapp.fragment.NoticeFragment;
import com.twinly.enotices.enoticesapp.fragment.PendingFragment;
import com.twinly.enotices.enoticesapp.utils.device.DeviceUtils;

public class ChildrenNoticeActivity extends AppCompatActivity {

    private RadioButton rbtn_pending;

    private RadioButton rbtn_notice;
    private RadioButton rbtn_event;
    private RadioButton rbtn_announ;
    private  RadioButton rbtn_more;

    private RadioGroup rg_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_notice);
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
                        NoticeFragment noticeFragment=NoticeFragment.newInstance("","");
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.replace(R.id.frame_fragments,noticeFragment,"notice_fragment");
                        transaction.commit();
                        break;
                    case R.id.rbtn_pending:
                        FragmentManager manager2=getFragmentManager();
                        PendingFragment pendingFragment=PendingFragment.newInstance("","");
                        FragmentTransaction transaction2=manager2.beginTransaction();
                        transaction2.replace(R.id.frame_fragments,pendingFragment,"notice_fragment");
                        transaction2.commit();
                        break;

                    case R.id.rbtn_announ:
                        FragmentManager manager3=getFragmentManager();
                        AnnouncementFragment announcementFragment=AnnouncementFragment.newInstance("","");
                        FragmentTransaction transaction3=manager3.beginTransaction();
                        transaction3.replace(R.id.frame_fragments,announcementFragment,"notice_fragment");
                        transaction3.commit();
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

        FragmentManager manager2=getFragmentManager();
        PendingFragment pendingFragment=PendingFragment.newInstance("","");
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
