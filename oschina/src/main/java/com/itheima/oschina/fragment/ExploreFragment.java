package com.itheima.oschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.view.findItemView;

/**
 * Created by fly on 2017/3/1.
 */

public class ExploreFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_explore_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
//        tv_content.setText("发现");

        View findItemView = view.findViewById(R.id.tuijian);

        findItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"你是不是傻",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
