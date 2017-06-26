package com.itheima.oschina.fragment.OpenSoftFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.adapter.fenLeiFragmentAdapter;
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

public class FenLeiAllFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.open_soft_allfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addFragmentToStack();

    }

    public void addFragmentToStack() {
        Fragment newFragment = FenLeiFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fl_opensoft_allfragment, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }


    //参考的代码-------------
    public static class CountingFragment extends Fragment {
        int mNum;
        private static int[] sColorValue = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light};

        /**
         * Create a new instance of CountingFragment, providing "num" as an
         * argument.
         */
        static CountingFragment newInstance(int num) {
            CountingFragment f = new CountingFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its instance
         * number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_stack_layout, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView) tv).setText("Fragment #" + mNum);
            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            Log.v("TAG", "mNum % 3 " + mNum % 3);
            tv.setBackgroundColor(getActivity().getResources().getColor(sColorValue[mNum % 3]));
            return v;
        }


        /**
         * 刷新数据
         */
        private void reflashData() {
            String url = "http://www.oschina.net/action/api/softwarecatalog_list";

            HttpParams httpParams = new HttpParams();
            httpParams.put("tag", "0");

            HttpLoader.getInstance(getActivity()).get(url, httpParams, null, 0x11, new HttpLoader.HttpListener<String>() {
                @Override
                public void onGetResponseSuccess(int requestCode, String response) {
                    //System.out.println(response);
                    SoftwareCatalogList softwareCatalogList = XmlUtils.toBean(SoftwareCatalogList.class, response.getBytes());
                    // Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
//                fenLeiFragmentAdapter.clear();
//                fenLeiFragmentAdapter.addAll(softwareCatalogList.getSoftwarecataloglist());
//                fenLeiFragmentAdapter.notifyDataSetChanged();
//                rv_femlei.refreshComplete();
                }

                @Override
                public void onGetResponseError(int requestCode, VolleyError error) {
                    Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_LONG).show();
                }
            });


        }

        /**
         * 初始化revycleview
         */
//    private void initRecycleView() {
//        //
//        rv_femlei.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL,1, Color.GRAY));
//        //设置一个线性布局的管理器i
//        rv_femlei.setLayoutManager(new LinearLayoutManager(getActivity()));
//        //设置下拉刷新的样式
//        rv_femlei.setRefreshProgressStyle(ProgressStyle.LineScalePulseOut);
//        rv_femlei.setPullRefreshEnabled(false);
//        rv_femlei.setLoadingMoreEnabled(false);
//
////       rv_femlei.refresh();
//
//        fenLeiFragmentAdapter = new fenLeiFragmentAdapter(getActivity());
//        rv_femlei.setAdapter(fenLeiFragmentAdapter);
//    }
    }
}
