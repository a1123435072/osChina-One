package com.itheima.oschina.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.itheima.oschina.R;

/**
 * Created by jason on 2017/6/25.
 */

public class NewDatailActivity extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_datails);
        webview= (WebView) findViewById(R.id.webview);
        String url = getIntent().getStringExtra("url");
//        WebSettings settings = webview.getSettings();
//        settings.setSupportZoom(true);          //支持缩放
//        settings.setBuiltInZoomControls(true);  //启用内置缩放装置
//        settings.setJavaScriptEnabled(true);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        webview.loadUrl(url);
    }
}
