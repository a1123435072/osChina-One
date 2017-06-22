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

public class SubNewFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;

    private List<News>  items = new ArrayList<>();

    public SubNewFragmentAdapter(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_sub_new_fragment_item, parent, false);
        return new SubNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SubNewViewHolder  subNewViewHolder = (SubNewViewHolder) holder;
        subNewViewHolder.tv_content.setText(items.get(position).getTitle());



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

        private final ImageView iv_image;
        private final TextView tv_content;

        public SubNewViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }

    }



}
