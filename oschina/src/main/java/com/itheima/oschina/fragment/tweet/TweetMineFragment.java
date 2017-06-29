package com.itheima.oschina.fragment.tweet;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import android.widget.Button;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.activity.LoginActivity;
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

    private static XRecyclerView mRecyclerView;
    private static TweetNewFragmentAdapter tweetNewFragmentAdapter;
    private static boolean isPullRefresh;
    private static int pageIndex = 0;
    private  static String uid;
    private static List<Tweet> items = new ArrayList<>();
    private Button btn_login ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tweet_mine_fragment, container, false);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        uid = SPUtil.newInstance(getActivity()).getString("uid");
        if (TextUtils.isEmpty(uid)) {
            btn_login.setVisibility(View.VISIBLE);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().startActivity(new Intent(getContext(), LoginActivity.class));
                }
            });

        }else{
            btn_login.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        //不要加载更多，下面就不会多出分隔线
        mRecyclerView.setLoadingMoreEnabled(false);

        //设置recyclerview下拉刷新和加载更多的监听
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {//下拉刷新
                isPullRefresh = true;
                pageIndex = 0;
                requestData(getActivity());
            }

            @Override
            public void onLoadMore() {//上拉加载更多
                pageIndex++;
                requestData(getActivity());
            }
        });

        //设置初始化状态为刷新状态。作用：界面初始加载时，刷新数据。
        mRecyclerView.refresh();


        //设置适配器
        tweetNewFragmentAdapter = new TweetNewFragmentAdapter(getActivity(), getContext());
        mRecyclerView.setAdapter(tweetNewFragmentAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        uid = SPUtil.newInstance(getActivity()).getString("uid");
        if (TextUtils.isEmpty(uid)){
            return;
        }
        btn_login.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        requestData(getActivity());
    }

    /**
     * 网络加载数据
     */

    public static void requestData(Activity activity) {
        uid = SPUtil.newInstance(activity).getString("uid");
        if(TextUtils.isEmpty(uid)){
            return ;
        }
        String url = "http://www.oschina.net/action/api/tweet_list";
        HttpParams params = new HttpParams();
        params.put("uid",uid);
        params.put("pageIndex", pageIndex+"");
        params.put("pageSize", "20");

        HttpLoader.getInstance(activity).get(url, params, null, 0x13, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                TweetsList tweetsList = XmlUtils.toBean(TweetsList.class, response.getBytes());
                items.clear();
                tweetNewFragmentAdapter.clear();
                if (isPullRefresh) {
                    items.addAll(tweetsList.getList());
                    mRecyclerView.refreshComplete();
                    isPullRefresh = !isPullRefresh;

                }else{
                    items.addAll(tweetsList.getList());
                    mRecyclerView.loadMoreComplete();
                }
                tweetNewFragmentAdapter.addAll(items);
                tweetNewFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });


    }

}
