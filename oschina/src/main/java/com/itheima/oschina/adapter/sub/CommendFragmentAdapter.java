package com.itheima.oschina.adapter.sub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.itheima.oschina.R;
import com.itheima.oschina.activity.NewDatailActivity;
import com.itheima.oschina.bean.Blog;
import com.itheima.oschina.bean.News;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fly on 2017/3/1.
 */

public class CommendFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mActivity;

    private List<News> item=new ArrayList<>();

    public CommendFragmentAdapter(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_sub_commend, parent, false);
        return new SubNewViewHolder(view);
    }
    @Override
    public int getItemCount() {

            return item.size();
    }
    public void addAll(List<News> datas){
        if(datas!=null) {
            item.addAll(datas);
            notifyItemRangeInserted(item.size() - 1, getItemCount() + datas.size());
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SubNewViewHolder  subNewViewHolder = (SubNewViewHolder) holder;

        if(item!=null){
            subNewViewHolder.title.setText(item.get(position).getTitle());
            subNewViewHolder.tvcontent.setText(item.get(position).getBody());
            subNewViewHolder.nickName.setText(item.get(position).getAuthor());
        }
        subNewViewHolder.tvTimes.setText("4"+System.currentTimeMillis());
        subNewViewHolder.count.setText("10"+(new Random().nextInt(10)+1));

        subNewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mActivity,NewDatailActivity.class);
                intent.putExtra("url",item.get(position).getUrl());

                mActivity.startActivity(intent);
            }
        });
    }
    public void clear(){
        notifyItemRangeRemoved(1, getItemCount());
    }



    class SubNewViewHolder extends RecyclerView.ViewHolder{


        private final TextView tvcontent;
        private final TextView title;
        private final TextView nickName;
        private final TextView count;
        private final TextView tvTimes;

        public SubNewViewHolder(View itemView) {
            super(itemView);
            /**
             * 个性头像
             */
            /**
             *标题
             */
            title = (TextView) itemView.findViewById(R.id.tv_title);
            /**
             *内容主题
             */
            tvcontent = (TextView) itemView.findViewById(R.id.tv_content);
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
