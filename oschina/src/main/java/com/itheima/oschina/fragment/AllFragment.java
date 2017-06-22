package com.itheima.oschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.AllFragmentAdapter;
import com.itheima.oschina.fragment.sub.SubBlogFragment;
import com.itheima.oschina.fragment.sub.SubNewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2017/3/1.
 *
 * 综合fragment
 *
 *
 */

public class AllFragment extends android.support.v4.app.Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AllFragmentAdapter allAdapter;

    private List<Fragment> subFragments = new ArrayList<>();
    private List<String>  subTitles = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_new_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        allAdapter = new AllFragmentAdapter(getChildFragmentManager());


        //初始化子fragment
        initSubFragment();

        //初始化tablayout的标题
        initSubTitle();

        //绑定 viewpager 和 tablayout
        //注意： 初始化子fragment和标题之后，确保adapter中有数据，才能绑定。

        viewPagerBindTabLayout();



    }

    /**
     * viewpager  和 tablayout 进行绑定
     */
    private void viewPagerBindTabLayout() {
        viewPager.setAdapter(allAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    /**
     * 初始化子fragment
     *
     *
     */
    private void initSubFragment() {

            subFragments.add(new SubNewFragment());//添加资讯fragment
            subFragments.add(new SubBlogFragment());//添加博客fragment
        
            allAdapter.addAll(subFragments);

    }


    /**
     * 初始化标题
     */
    private void initSubTitle() {
        subTitles.add("资讯");
        subTitles.add("博客");

        allAdapter.addPagerTitles(subTitles);


    }




}
