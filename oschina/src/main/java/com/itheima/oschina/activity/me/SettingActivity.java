package com.itheima.oschina.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.view.findItemView;

import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.tv_huancun)
    findItemView tvHuancun;
    @BindView(R.id.rv_back)
    findItemView rvBack;
    @BindView(R.id.tv_fankui)
    findItemView tvFankui;
    @BindView(R.id.tv_us)
    findItemView tvUs;
    @BindView(R.id.tv_gengxin)
    findItemView tvGengxin;
    @BindView(R.id.tv_zhuxiao)
    findItemView tvZhuxiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_huancun, R.id.rv_back, R.id.tv_fankui, R.id.tv_us, R.id.tv_gengxin, R.id.tv_zhuxiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_huancun:
                Toast.makeText(SettingActivity.this,"恭喜您清除缓存成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rv_back:
                break;
            case R.id.tv_fankui:
                break;
            case R.id.tv_us:
                break;
            case R.id.tv_gengxin:
                break;
            case R.id.tv_zhuxiao:
                SPUtil.newInstance(SettingActivity.this).clear();
                CookieManager.clearCookie(SettingActivity.this);
                Toast.makeText(SettingActivity.this,"恭喜您注销成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
