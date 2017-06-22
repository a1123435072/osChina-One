package com.itheima.oschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.AllFragmentAdapter;
import com.itheima.oschina.adapter.TweetFragmentAdapter;
import com.itheima.oschina.fragment.sub.SubBlogFragment;
import com.itheima.oschina.fragment.sub.SubNewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2017/3/1.
 */

public class TweetFragment extends android.support.v4.app.Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TweetFragmentAdapter tweetAdapter;

    private List<Fragment> subFragments = new ArrayList<>();
    private List<String>  subTitles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tweet_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tweetAdapter = new TweetFragmentAdapter(getChildFragmentManager());
        initSubTitle();
        initSubFragment();
        viewPagerBindTabLayout();
    }

    private void viewPagerBindTabLayout() {
        viewPager.setAdapter(tweetAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //初始化子标题
    private void initSubTitle() {
        subTitles.add("最新动弹");
        subTitles.add("热门动弹");
        subTitles.add("我的动弹");
        tweetAdapter.addPagerTitles(subTitles);
    }

    //初始化子fragment
    private void initSubFragment() {
        subFragments.add(new SubNewFragment());//添加最新动弹fragment
        subFragments.add(new SubBlogFragment());//添加fragment
        tweetAdapter.addAll(subFragments);
    }
}
