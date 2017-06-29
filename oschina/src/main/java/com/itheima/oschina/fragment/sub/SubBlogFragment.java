package com.itheima.oschina.fragment.sub;


import android.support.v7.widget.RecyclerView;


import com.itheima.oschina.adapter.sub.BlogFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.BlogList;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.xutil.XmlUtils;

/**
 * Created by jason on 2017/6/22.
 */

public class SubBlogFragment extends baseFragment {

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
            requestData(0x24,"blog_list","","20","4","","","week&pag");
        }else{
            pageIndex++;
            requestData(0x24,"blog_list","","20","4","","","week&pag");
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
        BlogList blogList = XmlUtils.toBean(BlogList.class, response.getBytes());
        if(isPullResfresh){
            blogFragmentAdapter.clear();
            blogFragmentAdapter.addAll(blogList.getList());
            isPullResfresh =!isPullResfresh;
            mRecyclerView.refreshComplete();
        }else{
            blogFragmentAdapter.addAll(blogList.getList());
            mRecyclerView.loadMoreComplete();
        }
    }
}
