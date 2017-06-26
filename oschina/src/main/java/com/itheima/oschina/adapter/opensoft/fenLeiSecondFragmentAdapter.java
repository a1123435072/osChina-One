package com.itheima.oschina.adapter.opensoft;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.SoftwareCatalogList;
import com.itheima.oschina.fragment.OpenSoftFragments.FenLeiSecondFragment;
import com.itheima.oschina.fragment.OpenSoftFragments.FenLeiThreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.tag;

/**
 * Created by yangg on 2017/6/23.
 */

public class fenLeiSecondFragmentAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SoftwareCatalogList.SoftwareType> item = new ArrayList<>();
    private FragmentManager fragmentManager;
    public fenLeiSecondFragmentAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        fenLeiSecondFragmentAdapter.ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvTitleFenlei.setText(item.get(position).getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = item.get(position).getTag();
                Toast.makeText(context,"点击了条目"+tag,Toast.LENGTH_SHORT).show();
                addFragmentToStack(tag);


            }
        });
    }

    public void addFragmentToStack(int tag) {
        Fragment newFragment = FenLeiThreeFragment.newInstance(tag);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fl_opensoft_allfragment, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
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
