package com.itheima.oschina.fragment.OpenSoftFragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.adapter.fenLeiFragmentAdapter;
import com.itheima.oschina.adapter.opensoft.fenLeiSecondFragmentAdapter;
import com.itheima.oschina.bean.SoftwareCatalogList;
import com.itheima.oschina.view.RecycleViewDivider;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

/**
 * Created by yangg on 2017/6/23.
 */

public class FenLeiSecondFragment extends Fragment {

    private XRecyclerView rv_femlei;
    private fenLeiSecondFragmentAdapter fenLeiSecondFragmentAdapter;
    private Context context;
    private FragmentManager fragmentManager;
    private int tag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = getArguments() != null ? getArguments().getInt("tag") : 1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feilei_fragment, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_femlei = (XRecyclerView) view.findViewById(R.id.rv_openSoft_fenlei);
        fragmentManager = getFragmentManager();

        // rv_femlei.refresh();
        initRecycleView();

        //刷新数据
        reflashData();
    }


    /**
     * Create a new instance of CountingFragment, providing "num" as an
     * argument.
     */
    public static FenLeiSecondFragment newInstance(int tag) {
        FenLeiSecondFragment f = new FenLeiSecondFragment();

       // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("tag", tag);
        f.setArguments(args);
        return f;
    }
    /**
     * 刷新数据
     */
    private void reflashData() {
        String  url = "http://www.oschina.net/action/api/softwarecatalog_list";

        HttpParams httpParams = new HttpParams();
        httpParams.put("tag",tag);

        HttpLoader.getInstance(getActivity()).get(url, httpParams, null, 0x11, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                //System.out.println(response);
                SoftwareCatalogList softwareCatalogList = XmlUtils.toBean(SoftwareCatalogList.class, response.getBytes());
               // Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                fenLeiSecondFragmentAdapter.clear();
                fenLeiSecondFragmentAdapter.addAll(softwareCatalogList.getSoftwarecataloglist());
                fenLeiSecondFragmentAdapter.notifyDataSetChanged();
//                rv_femlei.refreshComplete();
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                Toast.makeText(getActivity(),"请求数据失败",Toast.LENGTH_LONG).show();
            }
        });



    }

    /**
     * 初始化revycleview
     */
    private void initRecycleView() {
        //
        rv_femlei.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL,1, Color.GRAY));
        //设置一个线性布局的管理器i
        rv_femlei.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置下拉刷新的样式
        rv_femlei.setRefreshProgressStyle(ProgressStyle.LineScalePulseOut);
        rv_femlei.setPullRefreshEnabled(false);
        rv_femlei.setLoadingMoreEnabled(false);

//       rv_femlei.refresh();

        fenLeiSecondFragmentAdapter = new fenLeiSecondFragmentAdapter(getActivity(),fragmentManager);
        rv_femlei.setAdapter(fenLeiSecondFragmentAdapter);
    }
}
