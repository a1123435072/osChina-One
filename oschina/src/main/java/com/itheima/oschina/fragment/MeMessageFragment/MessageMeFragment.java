package com.itheima.oschina.fragment.MeMessageFragment;

import android.app.FragmentBreadCrumbs;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.me.MessageMeAdapter;
import com.itheima.oschina.adapter.opensoft.recommendAdapter;
import com.itheima.oschina.view.RecycleViewDivider;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangg on 2017/6/27.
 */

public class MessageMeFragment extends Fragment {


    private XRecyclerView rv_message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment, container, false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_message = (XRecyclerView) view.findViewById(R.id.rv_messageMe);

        initRecycleView();
    }

    /**
     * 初始化Recycleview
     */
    private void initRecycleView() {
        rv_message.addItemDecoration(new RecycleViewDivider(getActivity(),LinearLayoutManager.HORIZONTAL
        ,1,Color.GRAY));
        rv_message.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_message.setRefreshProgressStyle(ProgressStyle.LineScalePulseOut);

        rv_message.setPullRefreshEnabled(true);
        rv_message.setLoadingMoreEnabled(true);

        rv_message.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }



            @Override
            public void onLoadMore() {
                refreshData();
            }
        });

        rv_message.refresh();
        MessageMeAdapter messageMeAdapter = new MessageMeAdapter(getActivity());
        rv_message.setAdapter(messageMeAdapter);
    }

    //请求网络刷新数据的放法
    private void refreshData() {
        String url ="";

        HttpHeaders headers = new HttpHeaders();

       // headers.put()

        HttpParams httpParams = new HttpParams();
        httpParams.put("url",url);
        //httpParams.put();

    }



}
