package com.itheima.oschina.adapter.sub;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jason on 2017/6/26.
 */

public class GridLayoutAdapter extends FragmentPagerAdapter{
    private android.app.Fragment fragment;
    public GridLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(android.app.Fragment fragment){
        this.fragment=fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {

        return 0;
    }
}
