package com.itheima.oschina.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.activity.OpenSoftActivity;
import com.itheima.oschina.activity.codeCloudActivity;
import com.itheima.oschina.view.findItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fly on 2017/3/1.
 */

public class ExploreFragment extends Fragment {

    @BindView(R.id.tuijian)
    findItemView tuijian;
    @BindView(R.id.ruanjian)
    findItemView ruanjian;
    @BindView(R.id.saoyisao)
    findItemView saoyisao;
    @BindView(R.id.yaoyiyao)
    findItemView yaoyiyao;
    @BindView(R.id.fujin)
    findItemView fujin;
    @BindView(R.id.xianxia)
    findItemView xianxia;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_explore_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
//        tv_content.setText("发现");

        /*View viewById = view.findViewById(R.id.tuijian);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),codeCloudActivity.class));
            }
        });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tuijian, R.id.ruanjian, R.id.saoyisao, R.id.yaoyiyao, R.id.fujin, R.id.xianxia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tuijian:
                Toast.makeText(getActivity(),"码云推荐",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),codeCloudActivity.class));
                break;
            case R.id.ruanjian:
               // Toast.makeText(getActivity(),"推荐",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),OpenSoftActivity.class));
                break;
            case R.id.saoyisao:
                break;
            case R.id.yaoyiyao:
                break;
            case R.id.fujin:
                break;
            case R.id.xianxia:
                break;
        }
    }
}
