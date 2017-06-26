package com.itheima.oschina.fragment.sub;

import android.support.v7.widget.RecyclerView;
import com.itheima.oschina.adapter.sub.CommendFragmentAdapter;
import com.itheima.oschina.adapter.sub.EveryDayFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.BlogList;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.xutil.XmlUtils;

/**
 * Created by jason on 2017/6/22.
 */

public class SubEveryDayBlogFragment extends baseFragment {
    //一个通用的适配器
    private EveryDayFragmentAdapter commendFragmentAdapter;

    private boolean isPullResfresh;

    @Override
        protected RecyclerView.Adapter getCommonAdapter() {
            commendFragmentAdapter = new EveryDayFragmentAdapter(getActivity());
            return commendFragmentAdapter;
    }
    //重写recyclerview下拉刷新和加载更多的监听
    @Override
    protected void requestdata(boolean isPullResfresh) {
        this.isPullResfresh = isPullResfresh;
        this.isPullResfresh =true;
        if(isPullResfresh){
            pageIndex=0;
            requestData(0x23,"blog_list",pageIndex+"","20","","latest","","");
        }else{
            pageIndex++;
            requestData(0x23,"blog_list",pageIndex+"","20","","latest","","");
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
            commendFragmentAdapter.clear();
            commendFragmentAdapter.addAll(blogList.getBloglist());
            isPullResfresh =!isPullResfresh;
            mRecyclerView.refreshComplete();
        }else{
            commendFragmentAdapter.addAll(blogList.getBloglist());
            mRecyclerView.loadMoreComplete();
        }
    }
}
