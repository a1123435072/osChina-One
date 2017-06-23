package com.itheima.oschina.fragment.sub;

import android.support.v7.widget.RecyclerView;
import com.itheima.oschina.adapter.sub.SubNewFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.xutil.XmlUtils;

/**
 * Created by jason on 2017/6/22.
 */

public class SubEveryDayBlogFragment extends baseFragment {
    private SubNewFragmentAdapter subNewFragmentAdapter;

    private boolean isPullResfresh;

    @Override
    protected RecyclerView.Adapter getCommonAdapter() {
        subNewFragmentAdapter = new SubNewFragmentAdapter(getActivity());
        return subNewFragmentAdapter;
    }

    @Override
    protected void requestdata(boolean isPullResfresh) {
        this.isPullResfresh = isPullResfresh;
        this.isPullResfresh =true;
        if(isPullResfresh){
            pageIndex=0;
            requestData(0x23);
        }else{
            pageIndex++;
            requestData(0x23);
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
            subNewFragmentAdapter.clear();
            subNewFragmentAdapter.addAll(newsList.getList());
            isPullResfresh =!isPullResfresh;
            mRecyclerView.refreshComplete();
        }else{
            subNewFragmentAdapter.addAll(newsList.getList());
            mRecyclerView.loadMoreComplete();
        }
    }
}
