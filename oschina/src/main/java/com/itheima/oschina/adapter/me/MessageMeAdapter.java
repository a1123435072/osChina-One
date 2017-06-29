package com.itheima.oschina.adapter.me;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima.oschina.R;

/**
 * Created by yangg on 2017/6/28.
 */

public class MessageMeAdapter  extends RecyclerView.Adapter {
    private Context context;

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

        //ViewHolder viewHolder= (ViewHolder) holder;

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
