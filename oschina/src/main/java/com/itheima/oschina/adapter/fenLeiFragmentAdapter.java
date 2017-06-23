package com.itheima.oschina.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.Software;
import com.itheima.oschina.bean.SoftwareCatalogList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by yangg on 2017/6/23.
 */

public class fenLeiFragmentAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SoftwareCatalogList.SoftwareType> item = new ArrayList<>();

    public fenLeiFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        fenLeiFragmentAdapter.ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvTitleFenlei.setText(item.get(position).getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了条目",Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addAll(List<SoftwareCatalogList.SoftwareType> datas){
        item.addAll(datas);
        notifyItemRangeInserted(item.size()-1,getItemCount()+datas.size());
    }
    public void clear(){
        notifyItemRangeRemoved(1,getItemCount());
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_fenlei)
        TextView tvTitleFenlei;
        @BindView(R.id.iv_title_right)
        ImageView ivTitleRight;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
