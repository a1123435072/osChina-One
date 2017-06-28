package com.itheima.oschina.activity.me;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.me.MessageAdapter;
import com.itheima.oschina.fragment.MeMessageFragment.MessageCommentFragment;
import com.itheima.oschina.fragment.MeMessageFragment.MessageMeFragment;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager vp;
    private List<Fragment> fragments = new ArrayList<>();
private List<String> titles = new ArrayList<>();
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        tablayout = (TabLayout) findViewById(R.id.tablayout_message);

        vp = (ViewPager) findViewById(R.id.viewpager_message);
        messageAdapter = new MessageAdapter(getSupportFragmentManager());

        //初始化标题
        initTablayout();
        //初始化fragment
        initFragment();

        bindTabViewPager();
    }

    /**
     * 绑定tab和viewpager
     */
    private void bindTabViewPager() {
         vp.setAdapter(messageAdapter);
        tablayout.setupWithViewPager(vp);
    }

    /**
     * 初始化标题
     */
    private void initTablayout() {
        titles.add("@我");
        titles.add("评论");
        titles.add("私信");
        messageAdapter.addAlltitle(titles);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragments.add(new MessageMeFragment());
        fragments.add(new MessageCommentFragment());
        fragments.add(new MessageMeFragment());
        messageAdapter.addAll(fragments);
    }


}
