package com.itheima.oschina.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.activity.LoginActivity;
import com.itheima.oschina.fragment.ExploreFragment;
import com.itheima.oschina.fragment.MeFragment;
import com.itheima.oschina.fragment.AllFragment;
import com.itheima.oschina.fragment.TweetFragment;

import org.senydevpkg.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragments;
    private TextView tv_toolbar_title;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("--------有sout的日志--------");

        //显示标题
        tv_toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);

        toolbar = (Toolbar) findViewById(R.id.too_bar);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(this);

        //点击底部tab，发布动弹的监听，加号
        TextView tv_post_tweet = (TextView) findViewById(R.id.tv_post_tweet);
        //设置点击事件
        tv_post_tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行一下判断，sp里面有没有uid，有的话就跳转到发布页面Share
                String uid = SPUtil.newInstance(MainActivity.this).getString("uid");
                if (TextUtils.isEmpty(uid)) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,TweetShareActivity.class));
                }
            }
        });

        //初始化fragments
        initFragments();
        //切换fragments
        switchFragments(0);
        //让第一个tab，默认是选中
        radioGroup.check(R.id.rb_new);

    }

    /**
     * 根据下标，切换fragment
     */
    private void switchFragments(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        for(int i = 0; i < fragments.size(); i++){
            Fragment fragment = fragments.get(i);
            if(i == index){//选中的fragment
                if(fragment.isAdded()){//判断当前是否已经添加到容器中
                    //如果已经添加到容器中，直接显示
                    fragmentTransaction.show(fragment);
                }else{
                    //将fragment添加到容器中
                    fragmentTransaction.add(R.id.fl_container, fragment);
                }
            }else{//其它没有被选中
                //如果在容器中，包含了fragment，隐藏fragment
                if(fragment.isAdded()){
                    fragmentTransaction.hide(fragment);
                }
            }
        }
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    /**
     * 初始化fragment
     */
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new TweetFragment());
        fragments.add(new ExploreFragment());
        fragments.add(new MeFragment());

    }

    //资讯， 动弹， 发现，我的，相互切换监听
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.rb_new://资讯
                switchFragments(0);
                tv_toolbar_title.setText("综合");
                break;
            case R.id.rb_tweet://动弹
                switchFragments(1);
                tv_toolbar_title.setText("动弹");
                break;
            case R.id.rb_explore://发现
                switchFragments(2);
                tv_toolbar_title.setText("发现");
                break;
            case R.id.rb_me://我的
                switchFragments(3);
                //tv_toolbar_title.setText("我的");
                toolbar.setVisibility(View.GONE);
                break;
        }
    }
}
