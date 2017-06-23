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
import com.itheima.oschina.adapter.sub.SubNewFragmentAdapter;
import com.itheima.oschina.bean.News;
import com.itheima.oschina.bean.Tweet;
import com.itheima.oschina.xutil.bitmap.BitmapUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raynwang on 2017/6/22.
 */

public class TweetNewFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;
    Context context;

    private List<Tweet> items = new ArrayList<>();

    public TweetNewFragmentAdapter(Activity activity, Context context) {
        this.mActivity = activity;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_tweet_new_fragment_item, parent, false);
        return new TweetNewFragmentAdapter.TweetNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TweetNewFragmentAdapter.TweetNewViewHolder tweetNewViewHolder = (TweetNewFragmentAdapter.TweetNewViewHolder) holder;
        tweetNewViewHolder.tv_content.setText(items.get(position).getBody());
        tweetNewViewHolder.tv_id.setText(items.get(position).getAuthor());
        tweetNewViewHolder.tv_time.setText(items.get(position).getPubDate());
        ImageView imageView = tweetNewViewHolder.iv_head;
//        BitmapUtils.display(context,imageView,items.get(position).getPortrait());

        String urlPortrait = items.get(position).getPortrait();
        if (!TextUtils.isEmpty(urlPortrait)) {
            Picasso.with(context).load(urlPortrait).into(imageView);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //在哪用的？？？
    public void addAll(List<Tweet> datas) {
        items.addAll(datas);
        notifyItemRangeInserted(items.size() - 1, getItemCount() + datas.size());
    }

    //在哪用的？？？
    public void clear() {
        notifyItemRangeRemoved(1, getItemCount());
    }

    class TweetNewViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_content;
        private final TextView tv_id;
        private final TextView tv_time;
        private final ImageView iv_head;


        public TweetNewViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }

    }
}
