package com.twinly.enotices.enoticesapp.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.twinly.enotices.enoticesapp.fragment.NoticelistFragment;

/**
 * Created by jbhuang on 2/13/2018.
 */

public class AnnouncementPagerAdapter extends FragmentStatePagerAdapter {
    public AnnouncementPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new NoticelistFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
