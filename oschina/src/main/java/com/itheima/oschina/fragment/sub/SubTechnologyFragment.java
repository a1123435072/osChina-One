package com.itheima.oschina.fragment.sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.itheima.oschina.R;

/**
 * Created by jason on 2017/6/22.
 */

public class SubTechnologyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sub_tech,container,false);
        return view;
    }
}
