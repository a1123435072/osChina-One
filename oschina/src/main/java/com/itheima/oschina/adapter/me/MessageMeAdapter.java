package com.itheima.oschina.adapter.me;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.Active;
import com.itheima.oschina.bean.ActiveList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangg on 2017/6/28.
 */

public class MessageMeAdapter  extends RecyclerView.Adapter {
    private Context context;

    private List<Active> item = new ArrayList<>();

    public MessageMeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.message_me_item, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MessageMeAdapter.ViewHolder viewHolder= (ViewHolder) holder;

//        viewHolder.itemView.
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    /**
     * adapter 中清理条目的方法
     */
    public void clear() {
        notifyItemRangeRemoved(-1,getItemCount());
    }
    public void addAll(List<Active> data){
        item.addAll(data);
        notifyItemRangeInserted(item.size()-1,getItemCount()+data.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
