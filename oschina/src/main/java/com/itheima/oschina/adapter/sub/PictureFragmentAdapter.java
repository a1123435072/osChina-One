package com.itheima.oschina.adapter.sub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.activity.NewDatailActivity;
import com.itheima.oschina.bean.Blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fly on 2017/3/1.
 */

public class PictureFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;

    private List<Blog>  items = new ArrayList<>();


    public PictureFragmentAdapter(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_sub_new_fragment_item, parent, false);
        return new SubBlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        SubBlogViewHolder  subBlogViewHolder = (SubBlogViewHolder) holder;
        subBlogViewHolder.iv_image.setImageResource(R.drawable.alien);
        subBlogViewHolder.title.setText(items.get(position).getTitle());
        subBlogViewHolder.tv_content.setText(items.get(position).getBody());
        subBlogViewHolder.nickName.setText(items.get(position).getAuthor());
        subBlogViewHolder.count.setText("10"+(new Random().nextInt(10)+1));
        subBlogViewHolder.tvTimes.setText("4"+System.currentTimeMillis());

        subBlogViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mActivity,NewDatailActivity.class);
                intent.putExtra("url",items.get(position).getUrl());

                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<Blog> datas){
        items.addAll(datas);
        notifyItemRangeInserted(items.size() -1, getItemCount() + datas.size());
    }


    public void clear(){
        notifyItemRangeRemoved(1, getItemCount());
    }


    class SubBlogViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_image;
        private final TextView tv_content;
        private final TextView title;
        private final TextView nickName;
        private final TextView count;
        private final TextView tvTimes;

        public SubBlogViewHolder(View itemView) {
            super(itemView);
            /**
             * 个性头像
             */
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            /**
             *标题
             */
            title = (TextView) itemView.findViewById(R.id.tv_title);
            /**
             *内容主题
             */
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            /**
             *用户昵称
             */
            nickName = (TextView) itemView.findViewById(R.id.nick_name);
            /**
             *点在次数count
             */
            count = (TextView) itemView.findViewById(R.id.count);
            /**
             *时间
             */
            tvTimes = (TextView) itemView.findViewById(R.id.tv_time);

         }

    }



}
