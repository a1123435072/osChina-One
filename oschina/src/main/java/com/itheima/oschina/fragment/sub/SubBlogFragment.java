package com.itheima.oschina.fragment.sub;


import android.support.v7.widget.RecyclerView;

import com.itheima.oschina.adapter.sub.CommendFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.xutil.XmlUtils;

/**
 * Created by jason on 2017/6/22.
 */

public class SubBlogFragment extends baseFragment {
    private CommendFragmentAdapter commendFragmentAdapter;
    private boolean isPullResfresh;

    @Override
    protected RecyclerView.Adapter getCommonAdapter() {
        commendFragmentAdapter = new CommendFragmentAdapter(getActivity());
        return commendFragmentAdapter;
    }

    @Override
    protected void requestdata(boolean isPullResfresh) {
        this.isPullResfresh = isPullResfresh;
        this.isPullResfresh =true;
        if(isPullResfresh){
            pageIndex=0;
            requestData(0x24,"news_list");
        }else{
            pageIndex++;
            requestData(0x24,"news_list");
        }
    }

    @Override
    protected boolean isLoadMore() {
        return true;
    }

    @Override
    protected boolean isPullRefresh() {
        return true;
    }

    @Override
    protected void refresh(String response) {
        NewsList newsList = XmlUtils.toBean(NewsList.class, response.getBytes());
        if(isPullResfresh){
            commendFragmentAdapter.clear();
            commendFragmentAdapter.addAll(newsList.getList());
            isPullResfresh =!isPullResfresh;
            mRecyclerView.refreshComplete();
        }else{
            commendFragmentAdapter.addAll(newsList.getList());
            mRecyclerView.loadMoreComplete();
        }
    }
}
