package com.itheima.oschina.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.oschina.R;
import com.itheima.oschina.view.CircleImageView;
import com.itheima.oschina.view.findItemView;

import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by fly on 2017/3/1.
 */

public class MeFragment extends Fragment {

    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.qrcode)
    ImageView qrcode;
    @BindView(R.id.iv_portrait)
    CircleImageView ivPortrait;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_tweet_size)
    TextView tvTweetSize;
    @BindView(R.id.ll_dongtan)
    LinearLayout llDongtan;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.tv_fensi)
    TextView tvFensi;
    @BindView(R.id.ll_fensi)
    LinearLayout llFensi;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.message)
    findItemView message;
    @BindView(R.id.blog)
    findItemView blog;
    @BindView(R.id.note)
    findItemView note;
    @BindView(R.id.action)
    findItemView action;
    @BindView(R.id.team)
    findItemView team;
    Unbinder unbinder;
    private String uid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SPUtil.newInstance(getActivity()).getString("uid");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_me_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_username);
        tv_content.setText("我是用户名");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.setting, R.id.qrcode, R.id.ll_dongtan, R.id.ll_shoucang, R.id.ll_guanzhu,
            R.id.ll_fensi, R.id.rl_all, R.id.message, R.id.blog, R.id.note, R.id.action, R.id.team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                break;
            case R.id.qrcode:
                break;
            case R.id.ll_dongtan:
                break;
            case R.id.ll_shoucang:
                break;
            case R.id.ll_guanzhu:
                break;
            case R.id.ll_fensi:
                break;
            case R.id.rl_all:
                break;
            case R.id.message:
                break;
            case R.id.blog:
                break;
            case R.id.note:
                break;
            case R.id.action:
                break;
            case R.id.team:
                break;
        }
    }

    public void requestData(){
        String url= "http://www.oschina.net/action/api/my_information";

        HttpParams params = new HttpParams();
        params.put("uid",uid);
        //params.put("user",)
    }
}
