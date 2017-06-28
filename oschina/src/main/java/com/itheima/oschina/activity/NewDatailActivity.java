package com.itheima.oschina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.itheima.oschina.R;

import javax.crypto.interfaces.PBEKey;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jason on 2017/6/25.
 */

public class NewDatailActivity extends AppCompatActivity {
    private WebView webview;
    private ProgressBar pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_datails);
        ButterKnife.bind(this);
        pb = (ProgressBar) findViewById(R.id.pb);
        webview = (WebView) findViewById(R.id.webview);

        String url = getIntent().getStringExtra("url");
        WebSettings settings = webview.getSettings();
//        settings.setSupportZoom(true);          //支持缩放
//        settings.setBuiltInZoomControls(true);  //启用内置缩放装置
//        settings.setJavaScriptEnabled(true);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb.setVisibility(view.GONE);
            }
        });
        webview.loadUrl(url);
    }

    @OnClick(R.id.iv_arrow)
    public void onViewClicked() {
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
