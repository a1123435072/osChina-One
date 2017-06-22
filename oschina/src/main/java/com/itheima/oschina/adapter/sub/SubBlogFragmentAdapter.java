package com.itheima.oschina.adapter.sub;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2017/3/1.
 */

public class SubBlogFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;

    private List<Blog>  items = new ArrayList<>();

    public SubBlogFragmentAdapter(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_sub_new_fragment_item, parent, false);
        return new SubBlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SubBlogViewHolder  subBlogViewHolder = (SubBlogViewHolder) holder;
        subBlogViewHolder.tv_content.setText(items.get(position).getTitle());



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

        public SubBlogViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }

    }



}
