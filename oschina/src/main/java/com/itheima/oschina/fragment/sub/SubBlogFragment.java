package com.itheima.oschina.fragment.sub;

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
import com.itheima.oschina.adapter.sub.SubNewFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.BlogList;
import com.itheima.oschina.adapter.sub.SubBlogFragmentAdapter;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

/**
 * Created by fly on 2017/3/3.
 */

public class SubBlogFragment extends baseFragment {
    //一个通用的适配器
    private SubBlogFragmentAdapter subBlogFragmentAdapter;

    private boolean isPullResfresh;

    @Override
    protected RecyclerView.Adapter getCommonAdapter() {
        subBlogFragmentAdapter = new SubBlogFragmentAdapter(getActivity());
        return subBlogFragmentAdapter;
    }
    //重写recyclerview下拉刷新和加载更多的监听
    @Override
    protected void requestdata(boolean isPullResfresh) {
        this.isPullResfresh = isPullResfresh;
        this.isPullResfresh =true;
        if(isPullResfresh){
            pageIndex=0;
            requestData(0x26,"blog_list");
        }else{
            pageIndex++;
            requestData(0x26,"blog_list");
        }
    }
    //重写确定上啦加载
    @Override
    protected boolean isLoadMore() {
        return true;
    }
    //重写确定下拉加载
    @Override
    protected boolean isPullRefresh() {
        return true;
    }
    //重写接口刷新的方法,网络加载
    @Override
    protected void refresh(String response) {
        BlogList blogList = XmlUtils.toBean(BlogList.class, response.getBytes());
        if(isPullResfresh){
            subBlogFragmentAdapter.clear();
            subBlogFragmentAdapter.addAll(blogList.getBloglist());
            isPullResfresh =!isPullResfresh;
            mRecyclerView.refreshComplete();
        }else{
            subBlogFragmentAdapter.addAll(blogList.getBloglist());
            mRecyclerView.loadMoreComplete();
        }
    }
}
