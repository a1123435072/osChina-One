package com.itheima.oschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.utills.Constant;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

/**
 * Created by jason on 2017/6/22.
 */

public abstract class baseFragment extends Fragment {
    public XRecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;

    public int pageIndex = 0;
    private boolean ispullRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basefragmet, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.xrecyclerview);
        initRecyclerView();
    }

    public void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //设置recyclerview下拉刷新进度条的样式
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScalePulseOut);

        //设置recyclerview上拉加载更多的样式
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineScalePulseOutRapid);

        //支持下拉刷新
        mRecyclerView.setPullRefreshEnabled(isPullRefresh());
        //支持加载更多
        mRecyclerView.setLoadingMoreEnabled(isLoadMore());


        //设置recyclerview下拉刷新和加载更多的监听
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {//下拉刷新
                requestdata(true);
            }

            @Override
            public void onLoadMore() {//上拉加载更多
                requestdata(false);
            }
        });

        //设置初始化状态为刷新状态。作用： 界面初始加载时，刷新数据。
        mRecyclerView.refresh();
        /**
         * 第一个图片的轮播图,如果没有头则添加一个头,如果没有
          */
        if(getHeadView() != null){
            mRecyclerView.addHeaderView(getHeadView());
        }

//        //设置适配器
//        subNewFragmentAdapter = new CommendFragmentAdapter(getActivity());
        mRecyclerView.setAdapter(getCommonAdapter());


    }

    protected abstract RecyclerView.Adapter getCommonAdapter();

    protected abstract void requestdata(boolean isPullResfresh);

    protected abstract boolean isLoadMore();

    protected abstract boolean isPullRefresh();

    /**
     * 网络加载数据
     */
    public void requestData(int number,String Url) {

        String url = Constant.PATH+Url;

        HttpParams params = new HttpParams();
        params.put("pageIndex", pageIndex + "");
        params.put("pageSize", "20");
        params.put("catalog", "1");

        HttpLoader.getInstance(getActivity()).get(url, params, null, number, new HttpLoader.HttpListener<String>() {

            @Override
            public void onGetResponseSuccess(int requestCode, String response) {

                refresh(response);
            }
            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
    }

    /**
     *
     * 获得一个头view,作为轮播图
     */
    public View getHeadView(){
       return  null;
    }

    /**
     *一个刷新数据的方法
     */
    protected abstract void refresh(String response) ;
}
