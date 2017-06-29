package com.itheima.oschina.fragment.MeMessageFragment;

import android.app.FragmentBreadCrumbs;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.adapter.me.MessageMeAdapter;
import com.itheima.oschina.adapter.opensoft.recommendAdapter;
import com.itheima.oschina.bean.ActiveList;
import com.itheima.oschina.bean.MyInformation;
import com.itheima.oschina.view.RecycleViewDivider;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;


/**
 * Created by yangg on 2017/6/27.
 */

public class MessageMeFragment extends Fragment {


    private XRecyclerView rv_message;
    private String uid;
    private MessageMeAdapter messageMeAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SPUtil.newInstance(getActivity()).getString("uid");
        Log.i("test",uid+"------");
    }

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
        messageMeAdapter = new MessageMeAdapter(getActivity());
        rv_message.setAdapter(messageMeAdapter);
    }

    //请求网络刷新数据的放法
    private void refreshData() {
        String url ="http://www.oschina.net/action/api/active_list";

        HttpHeaders headers = new HttpHeaders();

        headers.put("cookie",CookieManager.getCookie(getActivity()));

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid",uid);
        httpParams.put("pageIndex",0);
        //httpParams.put("catalog",2);带上就报错
        httpParams.put("pageSize",20);

        Log.i("test",uid+"------");
        //httpParams.put();

        HttpLoader.getInstance(getActivity())
                .get(url, httpParams, headers, 0x14, new HttpLoader.HttpListener<String>() {
                    @Override
                    public void onGetResponseSuccess(int requestCode, String response) {
                        ActiveList activeList = XmlUtils.toBean(ActiveList.class, response.getBytes());
                        //Toast.makeText(getActivity(), "请求数据成功"+response, Toast.LENGTH_LONG).show();

                        Log.i("test",response);
                        messageMeAdapter.clear();
                        messageMeAdapter.addAll(activeList.getList());
                        rv_message.refreshComplete();


                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {
                        Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_LONG).show();

                    }
                });


        /*final String url = "http://www.oschina.net/action/api/my_information";

        HttpParams params = new HttpParams();
        Log.i("test", uid);
        params.put("uid", uid);
        HttpHeaders headers = new HttpHeaders();
        headers.put("cookie", CookieManager.getCookie(getActivity()));
        //params.put("user",)
        HttpLoader.getInstance(getActivity())
                .get(url, params, headers, 0x11, new HttpLoader.HttpListener<String>() {
                    @Override
                    public void onGetResponseSuccess(int requestCode, String response) {
                        //System.out.println("-------------response-----------"+response);
                        MyInformation user = XmlUtils.toBean(MyInformation.class, response.getBytes());
                        //item.add();
                        //ser.
                        //System.out.println("-----------"+user);
                        tvUsername.setText(user.getUser().getName());
                        tvScore.setText("积分 " + user.getUser().getScore()+"");
                        tvTweetSize.setText(user.getUser().getFans() + "");
                        tvShoucang.setText(user.getUser().getFavoritecount() + "");
                        tvGuanzhu.setText(user.getUser().getFollowers() + "");
                        tvFensi.setText(user.getUser().getFans() + "");
                        // Toast.makeText(getActivity(),"请求数据成功"+response,Toast.LENGTH_SHORT).show();
                        if(user.getUser().getPortrait()!=null){
                            Picasso.with(getActivity()).load(user.getUser().getPortrait()).into(ivPortrait);
                        }
                        String gender = user.getUser().getGender();
                        if (Integer.parseInt(gender)==1){
                            ivGender.setImageResource(R.drawable.userinfo_icon_male);
                        }else {
                            ivGender.setImageResource(R.drawable.userinfo_icon_female);

                        }




                        //ivPortrait
                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {
                        Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }



}
