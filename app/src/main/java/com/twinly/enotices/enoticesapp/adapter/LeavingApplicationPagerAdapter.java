package com.twinly.enotices.enoticesapp.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.utils.L;
import com.twinly.enotices.enoticesapp.fragment.LeavingListFragment;
import com.twinly.enotices.enoticesapp.protocol.LEAVING_APPLICATION;

import java.util.ArrayList;

/**
 * Created by huangjinbin on 2018/3/9.
 */

public class LeavingApplicationPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<LEAVING_APPLICATION> pendingList=new ArrayList<LEAVING_APPLICATION>();
    private ArrayList<LEAVING_APPLICATION> historyList=new ArrayList<LEAVING_APPLICATION>();

    public LeavingApplicationPagerAdapter(FragmentManager fm,ArrayList<LEAVING_APPLICATION> plist,ArrayList<LEAVING_APPLICATION> hlist) {
        super(fm);
        pendingList=plist;
        historyList=hlist;
    }

    @Override
    public Fragment getItem(int position) {
        LeavingListFragment leavingListFragment=null;
        switch (position){
            case 0:
                leavingListFragment=LeavingListFragment.newInstance("","",pendingList);
                break;
            case 1:
                leavingListFragment= LeavingListFragment.newInstance("","",historyList);
                break;
        }
        return leavingListFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        // 最简单解决 notifyDataSetChanged() 页面不刷新问题的方法
        return POSITION_NONE;
    }
}
