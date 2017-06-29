package com.itheima.oschina.adapter.me;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.Active;
import com.itheima.oschina.bean.SoftwareCatalogList;
import com.itheima.oschina.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangg on 2017/6/28.
 */

public class MessageMeAdapter extends RecyclerView.Adapter {
    private Context context;

    private List<Active> item = new ArrayList<>();

    public MessageMeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.message_me_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MessageMeAdapter.ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.tvUsername.setText(item.get(position).getAuthor()+"");
        viewHolder.tvMe.setText(item.get(position).getMessage()+"");
        //viewHolder.tvPinglunMsg.setText(item.get(position).get);

    }


    @Override
    public int getItemCount() {
        Log.i("test",item.size()+"item-->>>>");
        return item.size();
    }

    /**
     * adapter 中清理条目的方法
     */
    public void clear() {
        notifyItemRangeRemoved(1, getItemCount());
    }

    public void addAll(List<Active> data) {
        item.addAll(data);
        notifyItemRangeInserted(item.size() - 1, getItemCount() + data.size());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_touxiang)
        CircleImageView ivTouxiang;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_me)
        TextView tvMe;
        @BindView(R.id.tv_pinglun)
        TextView tvPinglun;
        @BindView(R.id.tv_xiangguan)
        TextView tvXiangguan;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_pinglunCount)
        TextView tvPinglunCount;
        @BindView(R.id.tv_pinglun_msg)
        TextView tvPinglunMsg;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
