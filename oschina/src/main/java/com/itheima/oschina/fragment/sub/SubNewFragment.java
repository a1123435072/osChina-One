package com.itheima.oschina.fragment.sub;


import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.itheima.oschina.R;
import com.itheima.oschina.adapter.sub.ImageAdapter;
import com.itheima.oschina.adapter.sub.CommendFragmentAdapter;
import com.itheima.oschina.base.baseFragment;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.utills.Constant;
import com.itheima.oschina.xutil.XmlUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fly on 2017/3/1.
 * 资讯， 热点 公用的 fragment
 */

public class SubNewFragment extends baseFragment implements  ViewPager.OnPageChangeListener{
    //一个通用的适配器
    private CommendFragmentAdapter commendFragmentAdapter;

    private boolean isPullResfresh;
    private List<Integer> pictureList;
    private ViewPager swichImage;
    private TextView tvTitle;
    private LinearLayout container;
    private View view;
    private Handler handler =new Handler();
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if(swichImage!=null){
//                //切换
//                int currenItem=swichImage.getCurrentItem();
//                //判断是否在最后一页
//                if(currenItem==pictureList.size()-1){
//                    currenItem=0;
//                }else {
//                    currenItem++;
//                }
//                //轮播
//                swichImage.setCurrentItem(currenItem);
//            }
//            handler.sendEmptyMessageDelayed(0,3000);
//        }
//    };
    //设置一个开关
    boolean turn=false;
    private List<ImageView> imageViews;


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
            requestData(0x25,"news_list",pageIndex+"","20","1","","","");
        }else{
            pageIndex++;
            requestData(0x25,"news_list",pageIndex+"","20","1","","","");
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
        inihttp();
        initPicture();
        initPoint();
        return view;
    }
    private void inihttp(){
        String url="http://www.oschina.net/action/apiv2/banner?catalog=1";
        HttpParams params= new HttpParams();

        HttpLoader.getInstance(getContext()).get(url,params, null, 0x42, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                    processData(response);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
    }

    private void processData(String response) {
        Gson gson= new Gson();

    }

    private void initPicture() {
        //写一个装轮播图片的临时集合
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.alien);
        pictureList.add(R.drawable.anguished);
        pictureList.add(R.drawable.bear);
        pictureList.add(R.drawable.boar);
        initSwitchImageView();
    }
    private void initSwitchImageView() {
        imageViews = new ArrayList<>();
        int size=pictureList.size();
        Integer resId=0;
        for (int i = -1; i <size+1; i++) {
            if(i == -1){
                //添加最后的一张图片
                resId = pictureList.get(size - 1);
            }else if(i == size){
                //添加第一张图片
                resId = pictureList.get(0);
            }else{
               resId = pictureList.get(i);
            }
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(resId);
            imageViews.add(iv);
            tvTitle.setText("");
        }
        ImageAdapter adapter= new ImageAdapter(imageViews);
        swichImage.setCurrentItem(0,false);
        swichImage.setAdapter(adapter);
        starSwitch();
    }
    public class StaskTest implements  Runnable{

        @Override
        public void run() {
            if(swichImage!=null){
                int currentItem = swichImage.getCurrentItem();
                if(currentItem==pictureList.size()-1){
                    currentItem=0;
                }else{
                    currentItem++;
                }
                swichImage.setCurrentItem(currentItem);
            }
            swichImage.postDelayed(this,2000);
        }
    }
    //初始化点
    private void initPoint() {
        //清空容器里面的布局
        container.removeAllViews();
        for (int i = 0; i < pictureList.size(); i++) {
            //小圆点
            View view = new View(getContext());
            //设置背景颜色
            view.setBackgroundResource(R.drawable.point_gray_bg);
            //布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5,5);
            //右边距
            params.rightMargin = 10;
            //添加布局
            container.addView(view,params);
        }
        //让第一个点的背景为红色
        container.getChildAt(0).setBackgroundResource(R.drawable.point_red_bg);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //    如果轮播图选中下标position= 0,修正之后的下标值为：pageIndex = size - 1
//    如果轮播图选中的下标position = size+1，修正之后的下标值为：pageIndex = 0；
//    其他情况，修正下标为：pageIndex = position -1；
    @Override
    public void onPageSelected(int position) {
        //修正下标
        int pageIndex=0;
        //得到数据的大小
        final int size=pictureList.size();
        //在手动滑动时,
        if(position == 0){
            //
            pageIndex=size-1;
            final int num = size;
            new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    swichImage.post(new Runnable() {
                        @Override
                        public void run() {
                            swichImage.setCurrentItem(num,false);
                        }
                    });
                }
            }.start();

        }else if(position==size+1){
            //如果size到了最后,则把pageIndex置为0,也就是第一个角标
            pageIndex = 0;
            //修改位置,
            swichImage.setCurrentItem(1,false);
        }else {
            pageIndex=position-1;
        }

        //设置轮播图的点的背景
        int childCount=container.getChildCount();
        for (int i =0;i<childCount;i++){
        //通过索引,来获得每个圆点的位置
        View childView=container.getChildAt(i);
        if(pageIndex==i){
            childView.setBackgroundResource(R.drawable.point_red_bg);
        }else {
            childView.setBackgroundResource(R.drawable.point_gray_bg);
        }
    }
}

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //开始切换
    public void starSwitch(){
        if(!turn){
            handler.postDelayed(new StaskTest(),2000);
        }
    }
    //停止切换
    public void stopSwitch(){
        //清空消息队列
        handler.removeCallbacksAndMessages(null);
        turn=false;
    }
}
