package com.itheima.oschina.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.OpenSoftAdapter;
import com.itheima.oschina.fragment.OpenSoftFragments.FenLeiFragment;
import com.itheima.oschina.fragment.OpenSoftFragments.tuiJianFragment;

import com.itheima.oschina.fragment.sub.SubEveryDayBlogFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenSoftActivity extends AppCompatActivity {

    private List<Fragment> openSoftFragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private OpenSoftAdapter openSoftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_soft);
        ButterKnife.bind(this);

        openSoftAdapter = new OpenSoftAdapter(getSupportFragmentManager());

        //初始化Fragment
         initFragment();
        //初始化tablayout
         initTab();
        //初始化玩tablayout之后要进行tablayout的绑定
        bindtablayoutViewPaget();
    }

    /**
     * 将view和tablayout进行绑定
     */
    private void bindtablayoutViewPaget() {
        viewpager.setAdapter(openSoftAdapter);
        tablayout.setupWithViewPager(viewpager);
    }

    /**
     *  初始化furagment
     */
    private void initFragment() {
        initTab();
        openSoftFragments.add(new FenLeiFragment());//分类Fragment
        openSoftFragments.add(new tuiJianFragment());//添加博客fragment
        openSoftFragments.add(new SubEveryDayBlogFragment());//添加技术fragmet
        openSoftFragments.add(new SubEveryDayBlogFragment());//添加每日一博fragmet
        openSoftFragments.add(new SubEveryDayBlogFragment());//添加每日一博fragmet
        openSoftAdapter.addAll(openSoftFragments);


    }

    /**
     * 初始化tab
     */
    private void initTab() {
        titles.add("分类");
        titles.add("推荐");
        titles.add("最新");
        titles.add("热门");
        titles.add("国产");
        openSoftAdapter.addPagerTitles(titles);
    }


}
