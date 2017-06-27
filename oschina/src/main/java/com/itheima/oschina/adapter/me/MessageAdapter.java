package com.itheima.oschina.adapter.me;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangg on 2017/6/27.
 */

public class MessageAdapter extends FragmentPagerAdapter {
    private List<Fragment>  fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public MessageAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles.get(position);
    }

    public void addAll(List<Fragment> fragments){
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }

    public void addAlltitle(List<String> titles){
        this.titles = titles;
    }
}
