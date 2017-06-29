package com.itheima.oschina.fragment.OpenSoftFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.bean.SoftwareCatalogList;
import com.itheima.oschina.xutil.XmlUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;


/**
 * Created by yangg on 2017/6/23.
 */

public class FenLeiAllFragment extends Fragment {

    private FragmentTransaction ft;
    public static FragmentManager childFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.open_soft_allfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Button  back = (Button) view.findViewById(R.id.iv_back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // popFragmentFromStack();
//            }
//        });
        childFragmentManager = getChildFragmentManager();
        addFragmentToStack();


    }


    public void addFragmentToStack() {
        Fragment newFragment = FenLeiFragment.newInstance();
        ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.fl_opensoft_allfragment, newFragment);
       // ft.replace(R.id.fl_opensoft_allfragment, newFragment, newFragment.getClass().getSimpleName());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

//    public void popFragmentFromStack(){
////        getChildFragmentManager().popBackStack();
//
//        int backStackEntryCount = getChildFragmentManager().getBackStackEntryCount();
//        if(backStackEntryCount >1){
//
//                getChildFragmentManager().popBackStack();
//
//        }else{
//           getActivity().finish();
//        }
//    }




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

    }
}
