package com.itheima.oschina.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.oschina.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class TweetDetailsActivity extends AppCompatActivity {

    XRecyclerView xrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);
                                    //一个XRecyclerView

        xrecyclerView = (XRecyclerView) findViewById(R.id.rv_tweetDetails);

        //添加头部  楼主内容
        View view = View.inflate(this,R.layout.tweet_head,null);
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
             class MyViewholder extends RecyclerView.ViewHolder{

                 public MyViewholder(View itemView) {
                     super(itemView);
                 }
             }

        });
    }
}
