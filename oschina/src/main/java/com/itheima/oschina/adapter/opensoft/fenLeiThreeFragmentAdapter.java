package com.itheima.oschina.adapter.opensoft;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.activity.opensoft.RecommendActivity;
import com.itheima.oschina.activity.opensoft.WeburlShowActivity;
import com.itheima.oschina.bean.SoftwareDec;
import com.itheima.oschina.fragment.OpenSoftFragments.FenLeiSecondFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangg on 2017/6/23.
 */

public class fenLeiThreeFragmentAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SoftwareDec> item = new ArrayList<>();
    private FragmentManager fragmentManager;

    public fenLeiThreeFragmentAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_three_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvTitleFenlei.setText(item.get(position).getName());

        viewHolder.tvAssortSoftDescribe.setText(item.get(position).getDescription());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = item.get(position).getUrl();
               // Toast.makeText(context, "点击了条目"+url, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, RecommendActivity.class);
                intent.putExtra("url",url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addAll(List<SoftwareDec> datas) {
        item.addAll(datas);
        notifyItemRangeInserted(item.size() - 1, getItemCount() + datas.size());
    }

    public void clear() {
        notifyItemRangeRemoved(1, getItemCount());
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_fenlei)
        TextView tvTitleFenlei;
        @BindView(R.id.tv_assort_soft_describe)
        TextView tvAssortSoftDescribe;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
