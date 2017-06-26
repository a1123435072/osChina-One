package com.itheima.oschina.fragment.tweet;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.activity.LoginActivity;
import com.itheima.oschina.activity.MainActivity;
import com.itheima.oschina.adapter.tweet.TweetNewFragmentAdapter;
import com.itheima.oschina.bean.Tweet;
import com.itheima.oschina.bean.TweetsList;
import com.itheima.oschina.view.RecycleViewDivider;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raynwang on 2017/6/22.
 */

public class TweetMineFragment extends Fragment{

    private XRecyclerView mRecyclerView;
    private TweetNewFragmentAdapter tweetNewFragmentAdapter;
    private boolean isPullRefresh;
    private int pageIndex = 0;
    private int requestCode;
    private String uid;
    boolean flag = false;
    private List<Tweet> items = new ArrayList<>();

    public TweetMineFragment(int requestCode){
        this.requestCode = requestCode;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SPUtil.newInstance(getActivity()).getString("uid");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        uid = SPUtil.newInstance(getActivity()).getString("uid");
        View view;
        if (TextUtils.isEmpty(uid)){
            view = inflater.inflate(R.layout.layout_please_login, container, false);
            view.findViewById(R.id.bt_pleaseLogin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
            });
        }else {
            flag = true;
            view = inflater.inflate(R.layout.layout_sub_new_fragment, container, false);
                                                //就一个XRecyclerView
        }
//        View view = inflater.inflate(R.layout.layout_sub_new_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (flag) {
            mRecyclerView = (XRecyclerView) view.findViewById(R.id.xrecyclerview);

            //分隔线，需要一个自定义分隔线控件，在view里面
            mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),
                    LinearLayoutManager.HORIZONTAL, 1, Color.GRAY));

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //设置recyclerview下拉刷新进度条的样式
            mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScalePulseOut);

            //设置recyclerview上拉加载更多的样式
            mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineScalePulseOutRapid);

            //支持下拉刷新
            mRecyclerView.setPullRefreshEnabled(true);
            //支持加载更多
            mRecyclerView.setLoadingMoreEnabled(true);


            //设置recyclerview下拉刷新和加载更多的监听
            mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {//下拉刷新
                    isPullRefresh = true;
                    pageIndex = 0;
                    requestData();
                }

                @Override
                public void onLoadMore() {//上拉加载更多
                    pageIndex++;
                    requestData();
                }
            });

            mRecyclerView.setLoadingMoreEnabled(false);
            //设置初始化状态为刷新状态。作用：界面初始加载时，刷新数据。
            mRecyclerView.refresh();


            //设置适配器
            tweetNewFragmentAdapter = new TweetNewFragmentAdapter(getActivity(), getContext(),items);
            mRecyclerView.setAdapter(tweetNewFragmentAdapter);
        }else{

        }


    }

    /**
     * 网络加载数据
     */
    private void requestData() {

        String url = "http://www.oschina.net/action/api/tweet_list";

        HttpParams params = new HttpParams();
        params.put("uid",uid);
//        params.put("uid","3566723");
        params.put("pageIndex", pageIndex+"");
        params.put("pageSize", "20");

        HttpHeaders headers = new HttpHeaders();
        headers.put("cookie", CookieManager.getCookie(getActivity()));
        HttpLoader.getInstance(getActivity()).get(url, params, headers, requestCode, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                System.out.println(response);
                TweetsList tweetsList = XmlUtils.toBean(TweetsList.class, response.getBytes());

                if (isPullRefresh) {
                    tweetNewFragmentAdapter.clear();//让适配器清空一下数据
                    items.addAll(tweetsList.getList());
                    mRecyclerView.refreshComplete();

                    isPullRefresh = !isPullRefresh;

                }else{
                    items.addAll(tweetsList.getList());
                    mRecyclerView.loadMoreComplete();
                }
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });


    }
}
