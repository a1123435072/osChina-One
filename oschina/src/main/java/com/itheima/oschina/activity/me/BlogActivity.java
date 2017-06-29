package com.itheima.oschina.activity.me;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.activity.TweetDetailsActivity;
import com.itheima.oschina.activity.TweetShareActivity;
import com.itheima.oschina.adapter.me.MeBlogAdapter;
import com.itheima.oschina.adapter.tweet.TweetCommentAdapter;
import com.itheima.oschina.bean.BlogList;
import com.itheima.oschina.bean.CommentList;
import com.itheima.oschina.view.RecycleViewDivider;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;

public class BlogActivity extends AppCompatActivity {

    private int pageIndex = 0;
    private boolean isPullRefresh;
    MeBlogAdapter meBlogAdapter;
    XRecyclerView xrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        xrecyclerView = (XRecyclerView) findViewById(R.id.xrecyclerview);

        //添加下面的评论item
        String url = "http://www.oschina.net/action/api/userblog_list";

        HttpParams params = new HttpParams();
        String uid = SPUtil.newInstance(this).getString("uid");
        params.put("authoruid",uid);
        params.put("uid",uid);
        params.put("pageIndex", pageIndex+"");
        params.put("authorname","");
        params.put("pageSize", "20");

        HttpHeaders headers = new HttpHeaders();
        headers.put("cookie", CookieManager.getCookie(this));

        HttpLoader.getInstance(this).get(url, params, headers, 0x23, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                BlogList blogList = XmlUtils.toBean(BlogList.class, response.getBytes());
                meBlogAdapter.addAll(blogList.getList());
                meBlogAdapter.notifyDataSetChanged();//适配器添加了数据，但是没有刷新，有可能出不来
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
            }
        });
        //分隔线，需要一个自定义分隔线控件，在view里面
        xrecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, Color.GRAY));
        xrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        meBlogAdapter = new MeBlogAdapter(BlogActivity.this,getApplicationContext());
        xrecyclerView.setAdapter(meBlogAdapter);
    }
}
