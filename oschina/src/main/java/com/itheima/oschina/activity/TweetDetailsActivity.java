package com.itheima.oschina.activity;

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
import com.itheima.oschina.bean.Tweet;
import com.itheima.oschina.bean.TweetDetail;
import com.itheima.oschina.bean.TweetLike;
import com.itheima.oschina.bean.TweetLikeUserList;
import com.itheima.oschina.bean.TweetsList;
import com.itheima.oschina.bean.User;
import com.itheima.oschina.xutil.XmlUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);
        //一个XRecyclerView
//        tvName = (TextView) findViewById(R.id.tv_name);
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
        int id = getIntent().getIntExtra("id", 0);
        params.put("id", id);
        HttpLoader.getInstance(this).get(url, params, null, 0x11, new HttpLoader.HttpListener<String>() {
            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                TweetDetail tweetDetail = XmlUtils.toBean(TweetDetail.class, response.getBytes());
                Tweet tweet = tweetDetail.getTweet();

                //给头部item控件设置数据
                //先是楼主信息
                tvName.setText(tweet.getAuthor());
                tvTime.setText(tweet.getPubDate());
                tvCommentNumber.setText(tweet.getCommentCount() + "");
                tvLikeNumber.setText(tweet.getLikeCount() + "");
                tv_content.setText(tweet.getBody());
//                tv_content.setText("\u3000\u3000"+tweet.getBody());
                String urlPortrait = tweet.getPortrait();
                if (!TextUtils.isEmpty(urlPortrait)) {
                    Picasso.with(getApplicationContext()).load(urlPortrait).into(ivHead);
                }

                //再是点赞人数设置数据
                List<User> likeUser = tweet.getLikeUser();
                String str = "";
                int size = likeUser.size();
                for (int i = 0; i < size; i++) {
                    if (i == size - 1) {
                        str += likeUser.get(i).getName();
                        break;
                    }
                    str += likeUser.get(i).getName() + "、";
                }
                str = str + "等" + size +"人觉的很赞";
                tv_likeMan.setText(str);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });

        xrecyclerView.addHeaderView(view);


        //添加下面的评论item
        xrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xrecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = View.inflate(getApplicationContext(), R.layout.item_tweet_comment, null);
                return new MyViewholder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }

            class MyViewholder extends RecyclerView.ViewHolder {

                public MyViewholder(View itemView) {
                    super(itemView);
                }
            }

        });
    }

    @OnClick(R.id.iv_like)
    public void onViewClicked() {

    }
}
