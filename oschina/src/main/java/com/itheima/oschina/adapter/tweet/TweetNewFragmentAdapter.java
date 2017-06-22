package com.itheima.oschina.adapter.tweet;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.adapter.sub.SubNewFragmentAdapter;
import com.itheima.oschina.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raynwang on 2017/6/22.
 */

public class TweetNewFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;

    private List<News> items = new ArrayList<>();

    public TweetNewFragmentAdapter(Activity activity){
        this.mActivity = activity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_tweet_new_fragment_item, parent, false);
        return new TweetNewFragmentAdapter.TweetNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TweetNewFragmentAdapter.TweetNewViewHolder tweetNewViewHolder = (TweetNewFragmentAdapter.TweetNewViewHolder) holder;
        tweetNewViewHolder.tv_content.setText(items.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //在哪用的？？？
    public void addAll(List<News> datas){
        items.addAll(datas);
        notifyItemRangeInserted(items.size() -1, getItemCount() + datas.size());
    }

    //在哪用的？？？
    public void clear(){
        notifyItemRangeRemoved(1, getItemCount());
    }

    class TweetNewViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_image;
        private final TextView tv_content;

        public TweetNewViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }

    }
}
