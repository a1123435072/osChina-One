package com.itheima.oschina.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.OpenSoftAdapter;
import com.itheima.oschina.fragment.OpenSoftFragments.FenLeiAllFragment;
import com.itheima.oschina.fragment.OpenSoftFragments.HotFragment;
import com.itheima.oschina.fragment.OpenSoftFragments.MideInChinaNewFragment;
import com.itheima.oschina.fragment.OpenSoftFragments.VeryNewFragment;
import com.itheima.oschina.fragment.OpenSoftFragments.tuiJianFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenSoftActivity extends AppCompatActivity {


    private List<Fragment> openSoftFragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private OpenSoftAdapter openSoftAdapter;
    private FenLeiAllFragment fenLeiAllFragment;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_soft);
        ButterKnife.bind(this);
        ivBack = (ImageView) findViewById(R.id.iv_back);

        openSoftAdapter = new OpenSoftAdapter(getSupportFragmentManager());

        //初始化Fragment
        initFragment();
        //初始化tablayout
        initTab();
        //初始化玩tablayout之后要进行tablayout的绑定
        //初始化玩tablayout之后要进行tablayout的绑定

        bindtablayoutViewPaget();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backStackEntryCount = FenLeiAllFragment.childFragmentManager.getBackStackEntryCount();
                if (backStackEntryCount > 1) {

                    FenLeiAllFragment.childFragmentManager.popBackStack();
                    //System.out.println("----------------------------------");

                } else {
                    finish();
                }
            }
        });
    }

    /**
     * 将view和tablayout进行绑定
     */
    private void bindtablayoutViewPaget() {
        viewpager.setAdapter(openSoftAdapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onBackPressed() {
        //int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        int backStackEntryCount = FenLeiAllFragment.childFragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 1) {

            FenLeiAllFragment.childFragmentManager.popBackStack();
            //System.out.println("----------------------------------");

        } else {
            finish();
        }
        //super.onBackPressed();
    }


    /**
     * 初始化furagment
     */
    private void initFragment() {
        // initTab();
        fenLeiAllFragment = new FenLeiAllFragment();
        openSoftFragments.add(fenLeiAllFragment);//分类Fragment
        openSoftFragments.add(new tuiJianFragment());//添加博客fragment
        openSoftFragments.add(new VeryNewFragment());//添加技术fragmet
        openSoftFragments.add(new HotFragment());//添加每日一博fragmet
        openSoftFragments.add(new MideInChinaNewFragment());//添加每日一博fragmet
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


    @OnClick(R.id.iv_back)
    public void onClick() {
    }
}
