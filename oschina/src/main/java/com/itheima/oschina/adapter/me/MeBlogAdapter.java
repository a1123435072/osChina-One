package com.itheima.oschina.adapter.me;

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
import com.itheima.oschina.bean.Blog;
import com.itheima.oschina.bean.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raynwang on 2017/6/24.
 */

public class MeBlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mActivity;
    Context context;

    public MeBlogAdapter(Activity activity, Context context) {
        this.mActivity = activity;
        this.context = context;
    }

    List<Blog> list = new ArrayList<>();

    public void addAll(List<Blog> alist) {
        list.addAll(alist);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_me_blog, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewholder myViewholder = (MyViewholder) holder;
        myViewholder.tv_title.setText(list.get(position).getTitle());
        myViewholder.tv_content.setText(list.get(position).getBody());
        myViewholder.tv_name.setText(list.get(position).getAuthor());
        myViewholder.tv_time.setText(list.get(position).getPubDate());
        myViewholder.tv_commentCount.setText(list.get(position).getCommentCount()+"");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_name;
        TextView tv_time;
        TextView tv_commentCount;

        public MyViewholder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_commentCount = (TextView) itemView.findViewById(R.id.tv_commentCount);

        }
    }

}
