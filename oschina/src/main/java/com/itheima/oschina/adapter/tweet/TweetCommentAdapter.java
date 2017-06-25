package com.itheima.oschina.adapter.tweet;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raynwang on 2017/6/24.
 */

public class TweetCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mActivity;
    Context context;

    public TweetCommentAdapter(Activity activity, Context context) {
        this.mActivity = activity;
        this.context = context;
    }

    List<Comment> list = new ArrayList<>();

    public void addAll(List<Comment> alist) {
        list.addAll(alist);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = View.inflate(getApplicationContext(), R.layout.item_tweet_comment, null);
        View view = LayoutInflater.from(mActivity).inflate(
                R.layout.item_tweet_comment, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewholder myViewholder = (MyViewholder) holder;

        String urlPortrait = list.get(position).getPortrait();
        if (!TextUtils.isEmpty(urlPortrait)) {
            Picasso.with(context).load(urlPortrait).into(myViewholder.iv_head);
        }
        myViewholder.tv_name.setText(list.get(position).getAuthor());
        myViewholder.tv_time.setText(list.get(position).getPubDate());
        myViewholder.tv_content.setText(list.get(position).getContent());


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        ImageView iv_head;
        TextView tv_name;
        TextView tv_time;
        TextView tv_content;

        public MyViewholder(View itemView) {
            super(itemView);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);

        }
    }

}
