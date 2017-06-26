package com.itheima.oschina.adapter.sub;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2017/3/1.
 */

public class CommendFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;

    private List<News>  items = new ArrayList<>();

    public CommendFragmentAdapter(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_sub_commend, parent, false);
        return new SubNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SubNewViewHolder  subNewViewHolder = (SubNewViewHolder) holder;
        subNewViewHolder.title.setText(items.get(position).getTitle());
        subNewViewHolder.tvcontent.setText(items.get(position).getBody());
        subNewViewHolder.nickName.setText(items.get(position).getAuthor());

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<News> datas){
        items.addAll(datas);

        notifyItemRangeInserted(items.size() -1, getItemCount() + datas.size());
    }


    public void clear(){
        notifyItemRangeRemoved(1, getItemCount());
    }



    class SubNewViewHolder extends RecyclerView.ViewHolder{

       // private final ImageView ivimage;
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
          //  ivimage = (ImageView) itemView.findViewById(R.id.iv_image);
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
