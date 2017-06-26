package com.itheima.oschina.activity.opensoft;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.itheima.oschina.R;

/**
 * 发现 开源软件 推荐 加载web界面,
 */

public class RecommendActivity extends AppCompatActivity {

    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recommend);
        wb = (WebView) findViewById(R.id.wb_recommend);

        //初始化webview
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //监听加载完成
                //给网页里面的img标签添加点击事件
                //addPictureClick();
            }
        });

        wb.setWebChromeClient(new WebChromeClient());
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setJavaScriptEnabled(true);

        String url = getIntent().getStringExtra("url");
        //加载网页
        wb.loadUrl(url);
//        wb.addJavascriptInterface(new JsCallJava() {
//            @JavascriptInterface
//            @Override
//            public void openImage(String src) {
//                Intent intent = new Intent(getApplicationContext(), WeburlShowActivity.class);
//                intent.putExtra("url", src);
//                startActivity(intent);
//            }
//        },"imagelistener");

    }

    //添加点击事件的方法,代码{}wb
    private void addPictureClick() {
        //android调用js代码
        wb.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName(\"img\"); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "    objs[i].onclick=function()  " + "   " + " {  "
                + "        window.imagelistner.openImage(this.src);  "
                + "    }  " + "}" + "})()");

    }

    private interface JsCallJava {
        public void openImage(String src);
    }
}
