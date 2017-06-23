package com.itheima.oschina.adapter.tweet;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raynwang on 2017/6/23.
 */

//public class TweetDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Activity mActivity;
//    Context context;
//
//    private List<Tweet> items = new ArrayList<>();
//
//    public TweetDetailsAdapter(Activity activity, Context context) {
//        this.mActivity = activity;
//        this.context = context;
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_head_tweet,parent,false);
//        return new TweetDetailsAdapter.TweetDetailsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class TweetDetailsViewHolder extends RecyclerView.ViewHolder {
//        public TweetDetailsViewHolder(View view) {
//            super();
//        }
//    }
//}
