package com.itheima.oschina.fragment;

import android.app.Activity;
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
import com.itheima.oschina.activity.LoginActivity;
import com.itheima.oschina.activity.me.ActionActivity;
import com.itheima.oschina.activity.me.BlogActivity;
import com.itheima.oschina.activity.me.ErWweiMaActivity;
import com.itheima.oschina.activity.me.MessageActivity;
import com.itheima.oschina.activity.me.NoteActivity;
import com.itheima.oschina.activity.me.SettingActivity;
import com.itheima.oschina.activity.me.TeamActivity;
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

import static com.itheima.oschina.R.id.spacer;
import static com.itheima.oschina.R.id.text;
import static com.itheima.oschina.R.id.tv_username;
import static com.itheima.oschina.R.string.login;

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
    private TextView tvScore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SPUtil.newInstance(getActivity()).getString("uid");



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_me_fragment, container, false);
        tvScore = (TextView) view.findViewById(R.id.tv_score);
        unbinder = ButterKnife.bind(this, view);
        if (TextUtils.isEmpty(uid)) {
            //uid为空的时候我们跳转到新的界面
            // view = inflater.inflate(R.layout.layout_me_notlogin_fragment, container, false);
            tvScore.setVisibility(View.GONE);
            llDongtan.setVisibility(View.GONE);
            llShoucang.setVisibility(View.GONE);
            llShoucang.setVisibility(View.GONE);
            llGuanzhu.setVisibility(View.GONE);
            llFensi.setVisibility(View.GONE);

            tvUsername.setTextSize(18);
            tvUsername.setText("亲您忘记登陆了哦");

        } else {
            //uid不为空，加载 用户界面 加载数据
            hasUid = true;
        }

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

    @Override
    public void onResume() {
        uid = SPUtil.newInstance(getActivity()).getString("uid");
        if (!TextUtils.isEmpty(uid)){

            requestData();
            tvScore.setVisibility(View.VISIBLE);
            llDongtan.setVisibility(View.VISIBLE);
            llShoucang.setVisibility(View.VISIBLE);
            llShoucang.setVisibility(View.VISIBLE);
            llGuanzhu.setVisibility(View.VISIBLE);
            llFensi.setVisibility(View.VISIBLE);
        }else {
            tvScore.setVisibility(View.GONE);
            llDongtan.setVisibility(View.GONE);
            llShoucang.setVisibility(View.GONE);
            llShoucang.setVisibility(View.GONE);
            llGuanzhu.setVisibility(View.GONE);
            llFensi.setVisibility(View.GONE);
            tvUsername.setTextSize(18);
            tvUsername.setText("亲您还有没有登陆哦");
            ivPortrait.setImageResource(R.drawable.widget_dface);

        }
        super.onResume();
    }

    @OnClick({R.id.setting, R.id.qrcode, R.id.ll_dongtan, R.id.ll_shoucang, R.id.ll_guanzhu,
            R.id.ll_fensi, R.id.rl_all, R.id.message, R.id.blog, R.id.note, R.id.action, R.id.team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting://设置点击事件
                Intent intent6 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent6);
                break;
            case R.id.qrcode://二维码点击世纪那
                Intent intent7 = new Intent(getActivity(), ErWweiMaActivity.class);
                startActivity(intent7);
                break;
            case R.id.ll_dongtan://动弹`
                break;
            case R.id.ll_shoucang://收藏
                break;
            case R.id.ll_guanzhu://关注
                break;
            case R.id.ll_fensi://粉丝
                break;
            case R.id.rl_all://头像下面的布局点击登陆
                if (TextUtils.isEmpty(uid)){
                    Intent intent13 = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent13,110);
                }{

            }

                break;
            case R.id.message://消息布局   @我
                Intent intent1 = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent1);
                break;
            case R.id.blog://博客
                Intent intent2 = new Intent(getActivity(), BlogActivity.class);
                startActivity(intent2);
                break;
            case R.id.note://问答
                Intent intent3 = new Intent(getActivity(), NoteActivity.class);
                startActivity(intent3);
                break;
            case R.id.action://活动
                Intent intent4 = new Intent(getActivity(), ActionActivity.class);
                startActivity(intent4);
                break;
            case R.id.team://团队
                Intent intent5 = new Intent(getActivity(), TeamActivity.class);
                startActivity(intent5);
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
                        System.out.println("-------------response-----------"+response);
                        MyInformation user = XmlUtils.toBean(MyInformation.class, response.getBytes());
                        //item.add();
                        //ser.
                        //System.out.println("-----------"+user);
                        tvUsername.setText(user.getUser().getName());
                        tvScore.setText("积分 " + user.getUser().getScore()+"");
                        tvTweetSize.setText(user.getUser().getFans() + "");
                        tvShoucang.setText(user.getUser().getFavoritecount() + "");
                        tvGuanzhu.setText(user.getUser().getFollowers() + "");
                        tvFensi.setText(user.getUser().getFans() + "");
                        // Toast.makeText(getActivity(),"请求数据成功"+response,Toast.LENGTH_SHORT).show();
                        if(!TextUtils.isEmpty(user.getUser().getPortrait())){

                            Picasso.with(getActivity()).load(user.getUser().getPortrait()).into(ivPortrait);
                        }
                        String gender = user.getUser().getGender();
                        if (Integer.parseInt(gender)==1){
                            ivGender.setImageResource(R.drawable.userinfo_icon_male);
                        }else {
                            ivGender.setImageResource(R.drawable.userinfo_icon_female);

                        }




                        //ivPortrait
                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {
                        Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
