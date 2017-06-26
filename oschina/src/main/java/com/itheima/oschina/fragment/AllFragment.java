package com.itheima.oschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.AllFragmentAdapter;
import com.itheima.oschina.fragment.sub.SubBlogFragment;
import com.itheima.oschina.fragment.sub.SubEveryDayBlogFragment;
import com.itheima.oschina.fragment.sub.SubNewFragment;
import com.itheima.oschina.fragment.sub.SubTechnologyFragmentw;
import com.itheima.oschina.view.DragGridLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fly on 2017/3/1.
 * <p>
 * 综合fragment
 */

public class AllFragment extends Fragment {

    Unbinder unbinder;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AllFragmentAdapter allAdapter;

    private List<Fragment> subFragments = new ArrayList<>();
    private List<String> subTitles = new ArrayList<>();
    private ImageButton crossButton;
    private LinearLayout linearlayout;

    private boolean isShowCross;
    private LinearLayout linear;
    private DragGridLayout dragGridLayout;
    private DragGridLayout dragGridLayout2;
    private RelativeLayout relativeLayoutfunction;
    private TextView delete;
    private boolean deleteItem;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_new_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        //切换按钮
        crossButton = (ImageButton) view.findViewById(R.id.iv_more);

        linearlayout = (LinearLayout) view.findViewById(R.id.linearlayout);
        //gridlayout所在的一个线性布局
        linear = (LinearLayout) view.findViewById(R.id.linear);
        //2个自定义的gridlayout
        dragGridLayout = (DragGridLayout) view.findViewById(R.id.dragGridLayout1);
        dragGridLayout2 = (DragGridLayout) view.findViewById(R.id.dragGridLayout2);
        //删除按钮
        delete = (TextView) view.findViewById(R.id.tv_delete);
        //切换后的上方控制台
        relativeLayoutfunction = (RelativeLayout) view.findViewById(R.id.RelativeLayoutfunction);
        allAdapter = new AllFragmentAdapter(getChildFragmentManager());
        //初始化子fragment
        initSubFragment();
        //初始化tablayout的标题
        initSubTitle();
        //绑定 viewpager 和 tablayout
        //注意： 初始化子fragment和标题之后，确保adapter中有数据，才能绑定。
        viewPagerBindTabLayout();
        //初始化gridLayout
        loadGridLayout();
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
     */
    private void initSubFragment() {
        subFragments.add(new SubNewFragment());//添加资讯fragment
        subFragments.add(new SubBlogFragment());//添加博客fragment
        subFragments.add(new SubTechnologyFragmentw());//添加技术fragmet
        subFragments.add(new SubEveryDayBlogFragment());//添加每日一博fragmet
        allAdapter.addAll(subFragments);
    }

    /**
     * 初始化标题
     */
    private void initSubTitle() {
        subTitles.add("开源资讯");
        subTitles.add("推荐博客");
        subTitles.add("技术问答");
        subTitles.add("每日一博");
        allAdapter.addPagerTitles(subTitles);
        //allAdapter.addPagerTitles(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    List <String> list;
    public void loadGridLayout() {
        dragGridLayout.setHasCanDrag(true);
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        dragGridLayout.setItems(list1);
        List<String> list2 = new ArrayList<>();
        list1.add("c");
        list1.add("d");
        list1.add("e");
        list1.add("f");
        list1.add("g");
        list1.add("h");
        list1.add("i");
        list1.add("g");
        list1.add("k");
        list2.add("l");
        list2.add("m");
        list2.add("n");
        list2.add("4");
        list2.add("o");
        list2.add("p");
        list2.add("q");
        list2.add("r");
        list2.add("s");
        list2.add("t");
        dragGridLayout2.setItems(list2);
        dragGridLayout2.setHasCanDrag(false);
        //设置条目点击监听
        dragGridLayout.setOnDragItemClickListener(new DragGridLayout.OnDragItemClickListener() {
            @Override
            public void onDragItemClick(TextView tv) {
                //移除点击的条目，把条目添加到下面的Gridlayout
                dragGridLayout.removeView(tv);//移除是需要时间,不能直接添加
                dragGridLayout2.addItem(tv.getText().toString());

            }
        });
        dragGridLayout2.setOnDragItemClickListener(new DragGridLayout.OnDragItemClickListener() {
            @Override
            public void onDragItemClick(TextView tv) {
                //移除点击的条目，把条目添加到上面的Gridlayout
                dragGridLayout2.removeView(tv);//移除是需要时间,不能直接添加
                dragGridLayout.addItem(tv.getText().toString());

            }
        });
    }

    @OnClick({R.id.tv_delete, R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
               if(deleteItem){
                   dragGridLayout2.setVisibility(View.VISIBLE);
                   deleteItem=!deleteItem;
               }else {
                   dragGridLayout2.setVisibility(View.GONE);
                   deleteItem=!deleteItem;
               }
                break;
            case R.id.iv_more:
            if(isShowCross) {
                viewPager.setVisibility(View.GONE);
                linear.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
                relativeLayoutfunction.setVisibility(View.VISIBLE);
                isShowCross = !isShowCross;
             } else{
                 viewPager.setVisibility(View.VISIBLE);
                 linear.setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                relativeLayoutfunction.setVisibility(View.GONE);
                isShowCross = !isShowCross;
        }break;
        }
    }

}
