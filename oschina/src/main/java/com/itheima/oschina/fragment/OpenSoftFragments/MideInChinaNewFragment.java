package com.itheima.oschina.fragment.OpenSoftFragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.adapter.opensoft.recommendAdapter;
import com.itheima.oschina.bean.SoftwareList;
import com.itheima.oschina.view.RecycleViewDivider;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

/**
 * Created by yangg on 2017/6/23.
 */

public class MideInChinaNewFragment extends Fragment {


    private XRecyclerView rv_tuijian;
    private int pageIndex = 0;
    private boolean isPullRefresh;
    private recommendAdapter recommendAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tuijian_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_tuijian = (XRecyclerView) view.findViewById(R.id.rv_tuijain);
        // reFreshData();

        //初始化recycleview
        initRecycleView();
        //reFreshData();
    }

    //xian
    private void initRecycleView() {
        //给recycleview画一条分割线  水平的分割线
        rv_tuijian.addItemDecoration(new RecycleViewDivider(getActivity()
                , LinearLayoutManager.HORIZONTAL
                , 1, Color.GRAY));
        //设置线性布局
        rv_tuijian.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置加载更多的样式
        rv_tuijian.setRefreshProgressStyle(ProgressStyle.LineScalePulseOut);
        //允许下拉刷新数据
        rv_tuijian.setPullRefreshEnabled(true);
        //允许下拉刷新数据
        rv_tuijian.setLoadingMoreEnabled(true);

        //设置自动刷新
        //rv_tuijian.refresh();
        //  rv_tuijian.refreshComplete();
        //设置上啦和下拉的监听,进行刷新数据
        rv_tuijian.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新数据
                isPullRefresh = true;
                reFreshData();
            }

            @Override
            public void onLoadMore() {
                reFreshData();
            }
        });

        rv_tuijian.refresh();

        //创建适配器对象
        recommendAdapter = new recommendAdapter(getActivity());
        rv_tuijian.setAdapter(recommendAdapter);
    }

    //下拉刷新数据
    private void reFreshData() {
        String url = "https://www.oschina.net/action/api/software_list";
        HttpParams params = new HttpParams();
        params.put("searchTag", "list_cn");

        HttpLoader.getInstance(getActivity())
                .get(url, params, null, 0x21, new HttpLoader.HttpListener<String>() {
                    @Override
                    public void onGetResponseSuccess(int requestCode, String response) {
                        SoftwareList softwareList = XmlUtils.toBean(SoftwareList.class, response.getBytes());

                        if (isPullRefresh) {
                            recommendAdapter.clear();
                            recommendAdapter.addAll(softwareList.getList());
                            rv_tuijian.refreshComplete();
                            isPullRefresh = !isPullRefresh;
                        } else {
                            recommendAdapter.addAll(softwareList.getList());
                            rv_tuijian.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {
                        Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
