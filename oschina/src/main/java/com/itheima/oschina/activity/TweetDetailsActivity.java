package com.itheima.oschina.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.adapter.tweet.TweetCommentAdapter;
import com.itheima.oschina.bean.CommentList;
import com.itheima.oschina.bean.Tweet;
import com.itheima.oschina.bean.TweetDetail;
import com.itheima.oschina.view.RecycleViewDivider;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;

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
    ImageView iv_imageSmall;
    ImageView iv_comment;
    EditText et_comment;
    ImageView iv_commentSend;
    LinearLayout ll_comment;

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
                                //标题加一个XRecyclerView
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
        tv_likeMan = (TextView) view.findViewById(R.id.tv_likeMan);
        iv_imageSmall = (ImageView) view.findViewById(R.id.iv_image);
        iv_comment = (ImageView) view.findViewById(R.id.iv_comment);

        //底部的评论三控件
        et_comment = (EditText) findViewById(R.id.et_comment);
        iv_commentSend = (ImageView) findViewById(R.id.iv_commentSend);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);

        //评论图标的点击事件
        iv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_comment.setVisibility(View.VISIBLE);
            }
        });

        //动弹详情，头部楼主信息
        String url = "http://www.oschina.net/action/api/tweet_detail";
        HttpParams params = new HttpParams();
        //从intent中获取id
        id = getIntent().getIntExtra("id", 0);
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

                String body = tweet.getBody().trim();
                //解析成Html
                Spanned html = Html.fromHtml(body);
                tv_content.setText(html);
                // 无需管他啥意思 加上这个东西 textview中的超链接就可以点击
                tv_content.setMovementMethod(LinkMovementMethod.getInstance());


//                Html.ImageGetter imgGetter = new Html.ImageGetter() {
//                    public Drawable getDrawable(String source) {
//                        Drawable drawable = null;
//                        System.out.println("-------------   "+source);
//                        URL url;
//                        try {
//                            url = new URL(source);
//                            drawable = Drawable.createFromStream(url.openStream(), "");
//                        } catch (Exception e) {
//                            return null;
//                        }
//                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                        return drawable;
//                    }
//                };
//                tv_content.setText(Html.fromHtml(body,imgGetter,null));
//                tv_content.setMovementMethod(LinkMovementMethod.getInstance());


                String urlPortrait = tweet.getPortrait();
                if (!TextUtils.isEmpty(urlPortrait)) {
                    Picasso.with(getApplicationContext()).load(urlPortrait).into(ivHead);
                }
                String urlImageSmall = tweet.getImgSmall();
                if (!TextUtils.isEmpty(urlImageSmall)) {
                    Picasso.with(getApplicationContext()).load(urlImageSmall).into(iv_imageSmall);
                }

                //小图点击事件，进入大图Activity
                iv_imageSmall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TweetDetailsActivity.this, BigImageActivity.class);
                        String urlImageBig = tweet.getImgBig();
                        intent.putExtra("urlImageBig",urlImageBig);
                        startActivity(intent);
                    }
                });

                //评论发送按钮的点击事件
                iv_commentSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String comment = et_comment.getText().toString();
                        if (TextUtils.isEmpty(comment)){
                            Toast.makeText(TweetDetailsActivity.this, "您还没写评论呢~", Toast.LENGTH_SHORT).show();
                        }else {
                            String url = "http://www.oschina.net/action/api/comment_pub";
                            HttpParams params = new HttpParams();
                            params.put("id",id);
                            params.put("catalog","3");
                            params.put("content",comment);
                            String uid = SPUtil.newInstance(TweetDetailsActivity.this).getString("uid");
                            params.put("uid",uid);

                            HttpHeaders headers = new HttpHeaders();
                            headers.put("cookie", CookieManager.getCookie(TweetDetailsActivity.this));

                            //这是发送评论的请求
                            HttpLoader.getInstance(getApplicationContext()).post(url, params, headers, 0x26, new HttpLoader.HttpListener<String>() {
                                @Override
                                public void onGetResponseSuccess(int requestCode, String response) {
                                    Toast.makeText(TweetDetailsActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
//                                    requestData();
//                                    xrecyclerView.refresh();

                                }

                                @Override
                                public void onGetResponseError(int requestCode, VolleyError error) {
                                    Toast.makeText(TweetDetailsActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });



                //再是点赞人数设置数据
//                List<User> likeUser = tweet.getLikeUser();
//                String str = "";
//                int size = likeUser.size();
//                if (size==1){
//                    str = likeUser.get(0).getName()+"觉得很赞";
//                }else if (size>1){
//                    for (int i = 0; i < size; i++) {
//                        if (i == size - 1) {
//                            str += likeUser.get(i).getName();
//                            break;
//                        }
//                        str += likeUser.get(i).getName() + "、";
//                    }
//                    str = str + "等" + size +"人觉的很赞";
//                }
                tweet.setLikeUsers(getApplicationContext(),tv_likeMan,true);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
        xrecyclerView.setLoadingMoreEnabled(false);//让它不要上拉加载，就不会在下面多一条分割线
        //把头部楼主部分添加到XRecyclerView上面
        xrecyclerView.addHeaderView(view);

        requestData();

        //分隔线，需要一个自定义分隔线控件，在view里面
        xrecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, Color.GRAY));
        xrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tweetCommentAdapter = new TweetCommentAdapter(TweetDetailsActivity.this,getApplicationContext());
        xrecyclerView.setAdapter(tweetCommentAdapter);

    }

    private void refresh() {
        finish();
        Intent intent = new Intent(TweetDetailsActivity.this, TweetDetailsActivity.class);
        startActivity(intent);

//        onCreate(null);
//        setContentView(R.layout.activity_tweet_details);
    }


    private void requestData(){
        //添加下面的评论item
        String url2 = "http://www.oschina.net/action/api/comment_list";
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
    }


}
