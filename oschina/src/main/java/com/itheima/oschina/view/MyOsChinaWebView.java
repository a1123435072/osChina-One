package com.itheima.oschina.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by jason on 2017/6/28.
 */

public class MyOsChinaWebView extends WebView {
    private  Context context;
    public MyOsChinaWebView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public MyOsChinaWebView(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context=context;
        init();
    }

    public MyOsChinaWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }
    @SuppressLint("JavascriptInterface")
    private void init(){
        //点击,否
        setClickable(false);
        //焦点,否
        setFocusable(false);
        //能否横拉,否
        setHorizontalScrollBarEnabled(false);

        WebSettings settings= getSettings();
        //设置webView字体的大小
        settings.setDefaultFontSize(14);
        //禁止webview缩放
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //支持js
        settings.setJavaScriptEnabled(true);
    }
}
