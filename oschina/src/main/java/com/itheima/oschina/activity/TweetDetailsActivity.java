package com.itheima.oschina.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.adapter.tweet.TweetCommentAdapter;
import com.itheima.oschina.bean.Comment;
import com.itheima.oschina.bean.CommentList;
import com.itheima.oschina.bean.Tweet;
import com.itheima.oschina.bean.TweetDetail;
import com.itheima.oschina.bean.TweetLike;
import com.itheima.oschina.bean.TweetLikeUserList;
import com.itheima.oschina.bean.TweetsList;
import com.itheima.oschina.bean.User;
import com.itheima.oschina.view.RecycleViewDivider;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TweetDetailsActivity extends AppCompatActivity {


    XRecyclerView xrecyclerView;

    ImageView ivHead;
    TextView tvName;
    TextView tvTime;
    TextView tvIphone;
    TextView tvCommentNumber;
    ImageView ivComment;
    TextView tvLikeNumber;
    ImageView ivLike;
    TextView tv_content;
    TextView tv_likeMan;

    private int pageIndex = 0;
    private boolean isPullRefresh;
    private TweetDetail tweetDetail;
    private Tweet tweet;
    int id;
    TweetCommentAdapter tweetCommentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);
                                //一个XRecyclerView
        xrecyclerView = (XRecyclerView) findViewById(R.id.rv_tweetDetails);
        //添加头部  楼主内容
//        View view = View.inflate(this, R.layout.tweet_head, null);
        View view = LayoutInflater.from(this).inflate(R.layout.tweet_head, null);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvIphone = (TextView) view.findViewById(R.id.tv_iphone);
        tvCommentNumber = (TextView) view.findViewById(R.id.tv_commentNumber);
        ivComment = (ImageView) view.findViewById(R.id.iv_comment);
        tvLikeNumber = (TextView) view.findViewById(R.id.tv_likeNumber);
        ivLike = (ImageView) view.findViewById(R.id.iv_like);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
//        iv_content_image = (ImageView) view.findViewById(R.id.iv_content_image);
        tv_likeMan = (TextView) view.findViewById(R.id.tv_likeMan);

        String url = "http://www.oschina.net/action/api/tweet_detail";
        HttpParams params = new HttpParams();
        //从intent中获取id
        id = getIntent().getIntExtra("id", 0);
        System.out.println("-----------------"+id);
        params.put("id", id);
        HttpLoader.getInstance(this).get(url, params, null, 0x11, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                tweetDetail = XmlUtils.toBean(TweetDetail.class, response.getBytes());
                tweet = tweetDetail.getTweet();

                //给头部item控件设置数据
                //先是楼主信息
                tvName.setText(tweet.getAuthor());
                tvTime.setText(tweet.getPubDate());
                tvCommentNumber.setText(tweet.getCommentCount() + "");
                tvLikeNumber.setText(tweet.getLikeCount() + "");
                tv_content.setText(tweet.getBody().trim());
//                tv_content.setText("\u3000\u3000"+tweet.getBody());
                String urlPortrait = tweet.getPortrait();
                if (!TextUtils.isEmpty(urlPortrait)) {
                    Picasso.with(getApplicationContext()).load(urlPortrait).into(ivHead);
                }

                //再是点赞人数设置数据
                List<User> likeUser = tweet.getLikeUser();
                String str = "";
                int size = likeUser.size();
                if (size==1){
                    str = likeUser.get(0).getName()+"觉得很赞";
                }else if (size>1){
                    for (int i = 0; i < size; i++) {
                        if (i == size - 1) {
                            str += likeUser.get(i).getName();
                            break;
                        }
                        str += likeUser.get(i).getName() + "、";
                    }
                    str = str + "等" + size +"人觉的很赞";
                }
                tv_likeMan.setText(str);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
        xrecyclerView.setLoadingMoreEnabled(false);//让它不要上拉加载，就不会在下面多一条分割线
        //把头部楼主部分添加到XRecyclerView上面
        xrecyclerView.addHeaderView(view);


        //添加下面的评论item
        String url2 = "http://www.oschina.net/action/api/comment_list";
        System.out.println("外部"+id);

        HttpParams params2 = new HttpParams();
        params2.put("pageIndex", pageIndex+"");
        params2.put("catalog", "3");
        params2.put("pageSize", "20");
        params2.put("id", id);//记得id是int值

        HttpLoader.getInstance(this).get(url2, params2, null, 0x25, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                CommentList commentList = XmlUtils.toBean(CommentList.class, response.getBytes());
//                System.out.println(commentList.getList().get(0).getAppClient());
                tweetCommentAdapter.addAll(commentList.getList());
                tweetCommentAdapter.notifyDataSetChanged();//适配器添加了数据，但是没有刷新，有可能出不来
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
            }
        });
        //分隔线，需要一个自定义分隔线控件，在view里面
        xrecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, Color.GRAY));
        xrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tweetCommentAdapter = new TweetCommentAdapter(TweetDetailsActivity.this,getApplicationContext());
        xrecyclerView.setAdapter(tweetCommentAdapter);

    }

    @OnClick(R.id.iv_like)
    public void onViewClicked() {

    }
}
