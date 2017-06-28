package com.itheima.oschina.fragment.sub;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.itheima.oschina.adapter.sub.BlogFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.xutil.XmlUtils;

/**
 * Created by jason on 2017/6/27.
 */

public class CategoryListFragment extends baseFragment {

    private int pageType;

    public static CategoryListFragment newInstance(int pageType) {
        CategoryListFragment f = new CategoryListFragment();
        Bundle b = new Bundle();

        b.putInt("pageType", pageType);
        f.setArguments(b);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            pageType = args.getInt("pageType");
        }
    }
    public void updateArguments(int pageType) {
        this.pageType=pageType;
        Bundle args = getArguments();
        if (args != null) {
            args.putInt("pageType", pageType);
        }
    }

    private BlogFragmentAdapter blogFragmentAdapter;

    private boolean isPullResfresh;

    @Override
    protected RecyclerView.Adapter getCommonAdapter() {
        blogFragmentAdapter = new BlogFragmentAdapter(getActivity());
        return blogFragmentAdapter;
    }

    @Override
    protected void requestdata(boolean isPullResfresh) {
        this.isPullResfresh = isPullResfresh;
        this.isPullResfresh =true;
        if(isPullResfresh){
            pageIndex=0;
            requestData(0x98,"news_list","","20","4","","","week&pag");
        }else{
            pageIndex++;
            requestData(0x98,"news_list","","20","4","","","week&pag");
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
            blogFragmentAdapter.clear();
            blogFragmentAdapter.addAll(newsList.getList());
            isPullResfresh =!isPullResfresh;
            mRecyclerView.refreshComplete();
        }else{
            blogFragmentAdapter.addAll(newsList.getList());
            mRecyclerView.loadMoreComplete();
        }
    }
}
