package com.itheima.oschina.fragment.sub;


import android.os.Handler;
import android.os.Message;
import android.support.annotation.Size;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.sub.ImageAdapter;
import com.itheima.oschina.adapter.sub.CommendFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.xutil.XmlUtils;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fly on 2017/3/1.
 * 资讯， 热点 公用的 fragment
 */

public class SubNewFragment extends baseFragment {
    //一个通用的适配器
    private CommendFragmentAdapter commendFragmentAdapter;

    private boolean isPullResfresh;
    private List<Integer> pictureList;
    private ViewPager swichImage;
    private TextView tvTitle;
    private LinearLayout container;
    private View view;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(swichImage!=null){
                //切换
                int currenItem=swichImage.getCurrentItem();
                Log.d("Tag", "currentItem = " + currenItem);
                //判断是否在最后一页
                if(currenItem==pictureList.size()-1){
                    currenItem=0;
                }else {
                    currenItem++;
                }
                //轮播
                swichImage.setCurrentItem(currenItem);
            }
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };
    //设置一个开关
    boolean turn=false;

    @Override
    protected RecyclerView.Adapter getCommonAdapter() {
        commendFragmentAdapter = new CommendFragmentAdapter(getActivity());
        return commendFragmentAdapter;
    }
    //重写recyclerview下拉刷新和加载更多的监听
    @Override
    protected void requestdata(boolean isPullResfresh) {
        this.isPullResfresh = isPullResfresh;
        this.isPullResfresh =true;
        if(isPullResfresh){
            pageIndex=0;
            requestData(0x25,"news_list");
        }else{
            pageIndex++;
            requestData(0x25,"news_list");
        }

    }
    //重写确定上啦加载
    @Override
    protected boolean isLoadMore() {
        return true;
    }
    //重写确定下拉加载
    @Override
    protected boolean isPullRefresh() {
        return true;
    }
    //重写接口刷新的方法,网络加载
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
    @Override
    public View getHeadView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_subnews_viewpage,null);
        swichImage = (ViewPager) view.findViewById(R.id.vp_switch_image);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        container = (LinearLayout) view.findViewById(R.id.ll_point_container);
        initPicture();
        initSwitchImageView();
        return view;
    }
    private void initPicture() {
        //写一个装轮播图片的临时集合
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.alien);
        pictureList.add(R.drawable.anguished);
        pictureList.add(R.drawable.bear);
        pictureList.add(R.drawable.boar);

    }

    private void initSwitchImageView() {
        List<ImageView>  imageViews = new ArrayList<>();
        for (int i = 0; i < pictureList.size(); i++) {
            Integer resId;
//            if(i == -1){//
//                //添加最后的一张图片
//                resId = pictureList.get(pictureList.size() - 1);
//            }else if(i ==  pictureList.size()){
//                //添加第一张图片
//                resId = pictureList.get(0);
//            }else{
               resId = pictureList.get(i);
//            }
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(resId);
            imageViews.add(iv);
        }

        tvTitle.setText("第n张图");

        ImageAdapter adapter= new ImageAdapter(imageViews,getContext());
        swichImage.setAdapter(adapter);

//        swichImage.setCurrentItem(1,false);

        starSwitch();
    }

    //开始切换
    public void starSwitch(){
        if(!turn){
            handler.sendEmptyMessageDelayed(0,3000);
        }
    }
    //停止切换
    public void stopSwitch(){
        //清空消息队列
        handler.removeCallbacksAndMessages(null);
        turn=false;
    }
}
