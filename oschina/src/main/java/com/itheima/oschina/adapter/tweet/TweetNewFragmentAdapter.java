package com.itheima.oschina.adapter.tweet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.activity.BigImageActivity;
import com.itheima.oschina.activity.TweetDetailsActivity;
import com.itheima.oschina.bean.Tweet;
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
                                                                        //最新动弹item
        return new TweetNewFragmentAdapter.TweetNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final TweetNewFragmentAdapter.TweetNewViewHolder tweetNewViewHolder = (TweetNewFragmentAdapter.TweetNewViewHolder) holder;

        tweetNewViewHolder.tv_content.setText(items.get(position).getBody().trim());
        tweetNewViewHolder.tv_name.setText(items.get(position).getAuthor());
        tweetNewViewHolder.tv_time.setText(items.get(position).getPubDate());
        tweetNewViewHolder.tv_commemntNumber.setText(items.get(position).getCommentCount());
        tweetNewViewHolder.tv_likeNumber.setText(items.get(position).getLikeCount()+"");
        items.get(position).setLikeUsers(context,tweetNewViewHolder.tv_likeUser,true);

//        BitmapUtils.display(context,imageView,items.get(position).getPortrait());

        //头像
        String urlPortrait = items.get(position).getPortrait();
        if (!TextUtils.isEmpty(urlPortrait)) {
            Picasso.with(context).load(urlPortrait).into(tweetNewViewHolder.iv_head);
        }else{//如果没有头像，就使用默认
            tweetNewViewHolder.iv_head.setImageResource(R.drawable.ic_share);
//            Bitmap bm = BitmapFactory.decodeFile(pirPath);
//            tweetNewViewHolder.iv_head.setImageBitmap(bm);
        }

        String urlImageSmall = items.get(position).getImgSmall();
        if (!TextUtils.isEmpty(urlImageSmall)) {
            Picasso.with(context).load(urlImageSmall).into(tweetNewViewHolder.iv_imageSmall);
        }else {//如果是空，就没有图片，就把ImageView给隐藏掉，否则会有图片复用的bug
            tweetNewViewHolder.iv_imageSmall.setVisibility(View.GONE);
        }

        //小图的点击事件，进入大图Activity
        tweetNewViewHolder.iv_imageSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BigImageActivity.class);
                String urlImageBig = items.get(position).getImgBig();
                intent.putExtra("urlImageBig",urlImageBig);
                context.startActivity(intent);
            }
        });

        //条目的点击事件，进入详情
        tweetNewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TweetDetailsActivity.class);
                int id = items.get(position).getId();
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<Tweet> datas) {
        items.clear();
        items.addAll(datas);
        notifyItemRangeInserted(items.size() - 1, getItemCount() + datas.size());
        //需要刷新一次，因为加载新的item时，itemCount需要相应变化
        notifyDataSetChanged();
    }

    public void clear() {
        notifyItemRangeRemoved(1, getItemCount());
        items.clear();
    }

    class TweetNewViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_content;
        private final TextView tv_name;
        private final TextView tv_time;
        private final ImageView iv_head;
        private final TextView tv_likeNumber;
        private final TextView tv_commemntNumber;
        private final TextView tv_likeUser;
        private final ImageView iv_imageSmall;

        public TweetNewViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_likeNumber = (TextView) itemView.findViewById(R.id.tv_likeNumber);
            tv_commemntNumber = (TextView) itemView.findViewById(R.id.tv_commentNumber);
            tv_likeUser = (TextView) itemView.findViewById(R.id.tv_likeUser);
            iv_imageSmall = (ImageView) itemView.findViewById(R.id.iv_image);
        }

    }
}
