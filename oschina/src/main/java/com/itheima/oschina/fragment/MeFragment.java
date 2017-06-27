package com.itheima.oschina.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.bean.MyInformation;
import com.itheima.oschina.view.CircleImageView;
import com.itheima.oschina.view.findItemView;
import com.itheima.oschina.xutil.XmlUtils;
import com.squareup.picasso.Picasso;

import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.itheima.oschina.R.id.tv_username;

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
    @BindView(tv_username)
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

    private boolean hasUid;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SPUtil.newInstance(getActivity()).getString("uid");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (TextUtils.isEmpty(uid)) {
            //uid为空的时候我们跳转到新的界面
            view = inflater.inflate(R.layout.layout_me_notlogin_fragment, container, false);
        } else {
            view = inflater.inflate(R.layout.layout_me_fragment, container, false);
            //uid不为空，加载 用户界面 加载数据
            hasUid = true;
        }
        unbinder = ButterKnife.bind(this, view);
        if (hasUid) {
            requestData();
        }else {

        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
            case R.id.setting://设置点击事件
                break;
            case R.id.qrcode://二维码点击世纪那
                break;
            case R.id.ll_dongtan://动弹
                break;
            case R.id.ll_shoucang://收藏
                break;
            case R.id.ll_guanzhu://关注
                break;
            case R.id.ll_fensi://粉丝
                break;
            case R.id.rl_all://头像下面的布局点击使劲
                break;
            case R.id.message://消息布局   @我
                    new Intent();
                break;
            case R.id.blog://博客
                break;
            case R.id.note://问答
                break;
            case R.id.action://活动
                break;
            case R.id.team://团队
                break;
        }
    }

    public void requestData() {
        final String url = "http://www.oschina.net/action/api/my_information";

        HttpParams params = new HttpParams();
        Log.i("test", uid);
        params.put("uid", uid);
        HttpHeaders headers = new HttpHeaders();
        headers.put("cookie", CookieManager.getCookie(getActivity()));
        //params.put("user",)
        HttpLoader.getInstance(getActivity())
                .get(url, params, headers, 0x11, new HttpLoader.HttpListener<String>() {


                    @Override
                    public void onGetResponseSuccess(int requestCode, String response) {
                        //System.out.println("-------------response-----------"+response);
                        MyInformation user = XmlUtils.toBean(MyInformation.class, response.getBytes());
                        //item.add();
                        //ser.
                        //System.out.println("-----------"+user);
                        tvUsername.setText(user.getUser().getName());
                        tvScore.setText("积分 " + user.getUser().getScore());
                        tvTweetSize.setText(user.getUser().getFans() + "");
                        tvShoucang.setText(user.getUser().getFavoritecount() + "");
                        tvGuanzhu.setText(user.getUser().getFollowers() + "");
                        tvFensi.setText(user.getNotice().getNewFansCount() + "");
                        // Toast.makeText(getActivity(),"请求数据成功"+response,Toast.LENGTH_SHORT).show();
                        String gender = user.getUser().getGender();
                        if (Integer.parseInt(gender)==1){
                            ivGender.setImageResource(R.drawable.userinfo_icon_male);
                        }else {
                            ivGender.setImageResource(R.drawable.userinfo_icon_female);

                        }

                        Picasso.with(getActivity()).load(user.getUser().getPortrait()).into(ivPortrait);

                        //ivPortrait
                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {
                        Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
