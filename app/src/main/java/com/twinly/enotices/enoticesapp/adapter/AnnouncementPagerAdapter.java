package com.twinly.enotices.enoticesapp.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;


import com.twinly.enotices.enoticesapp.fragment.ActivityListFragment;
import com.twinly.enotices.enoticesapp.fragment.NoticelistFragment;

/**
 * Created by jbhuang on 2/13/2018.
 */

public class AnnouncementPagerAdapter extends FragmentStatePagerAdapter {
    private String school_db;
    private String secret_id;
    private String type;

    public AnnouncementPagerAdapter(FragmentManager fm,String school_db,String secret_id, String type) {
        super(fm);
        this.school_db=school_db;
        this.secret_id=secret_id;
        this.type=type;
    }

    @Override
    public Fragment getItem(int position) {

        ActivityListFragment activityListFragment=null;
        Log.d("[FRAGMENT POSITION]","[position]: "+position);
        switch (position){
            case 0:
                activityListFragment=ActivityListFragment.newInstance(school_db,secret_id,type,"unread");
                break;

            case 1:
                activityListFragment=ActivityListFragment.newInstance(school_db,secret_id,type,"read");
                break;
            case 2:
                activityListFragment=ActivityListFragment.newInstance(school_db,secret_id,type,"starred");
                break;
            case 3:
                activityListFragment=ActivityListFragment.newInstance(school_db,secret_id,type,"all");
                break;
        }
        return activityListFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
