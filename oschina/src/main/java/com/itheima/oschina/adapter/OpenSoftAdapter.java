package com.itheima.oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by yangg on 2017/6/23.
 */

public class OpenSoftAdapter extends FragmentPagerAdapter{
    private List<Fragment> openSoftFragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public OpenSoftAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return openSoftFragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return openSoftFragments.size();
    }

    /**
     * 动态添加fragments
     * @param fragments
     */
    public void addAll(List<Fragment> fragments){
        this.openSoftFragments.addAll(fragments);
        notifyDataSetChanged();
    }

    /**
     * 初始化tablayout标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    /**
     * 动态添加 标题
     * @param title
     */
    public void addPagerTitles(List<String> title){
       this.titles.addAll(title);
    }
}
