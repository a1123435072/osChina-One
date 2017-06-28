package com.itheima.oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 综合界面的适配器
 */

public class AllFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> subFragments = new ArrayList<>();
    private List<String>   subTitles = new ArrayList<>();

    public AllFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return subFragments.get(position);
    }

    @Override
    public int getCount() {
        return subFragments.size();
    }

    /**
     * 初始化tablayout中每个tab标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return subTitles.get(position);
    }

    /**
     * 动态设置fragment
     * @param fragments
     */
    public void addAll(List<Fragment> fragments){
        this.subFragments.addAll(fragments);
    }

    /**
     * 动态设置标题
     * @param titles
     */
    public void addPagerTitles(List<String>  titles){
        subTitles.addAll(titles);
    }
}
