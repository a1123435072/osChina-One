package com.itheima.oschina.adapter.opensoft;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.activity.opensoft.RecommendActivity;
import com.itheima.oschina.bean.SoftwareCatalogList;
import com.itheima.oschina.bean.SoftwareDec;
import com.itheima.oschina.bean.SoftwareList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangg on 2017/6/25.
 */

public class recommendAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<SoftwareDec> item_tuijian = new ArrayList<>();
    public recommendAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_recommend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final String webUrl = item_tuijian.get(position).getUrl();
        recommendAdapter.ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvSoftName.setText(item_tuijian.get(position).getName());
        viewHolder.tvSoftDescribe.setText(item_tuijian.get(position).getDescription());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"点击了一个条目",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, RecommendActivity.class);
                intent.putExtra("url",webUrl);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item_tuijian.size();
    }

    /**
     * 自定义添加的方法
     */
    public void addAll(List<SoftwareDec> datas){
        item_tuijian.addAll(datas);
        notifyItemRangeInserted(item_tuijian.size()-1,getItemCount()+datas.size());
    }

    /**
     * 自定义清理的方法条目的放法
     */
    public void clear(){
        notifyItemRangeRemoved(1,getItemCount());
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_SoftName)
        TextView tvSoftName;
        @BindView(R.id.tv_softDescribe)
        TextView tvSoftDescribe;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
