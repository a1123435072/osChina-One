package com.itheima.oschina.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.bean.Blog;
import com.itheima.oschina.bean.BlogDetail;
import com.itheima.oschina.bean.News;
import com.itheima.oschina.bean.NewsDetail;
import com.itheima.oschina.utills.Constant;
import com.itheima.oschina.xutil.XmlUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;

import javax.crypto.interfaces.PBEKey;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jason on 2017/6/25.
 */

public class NewDatailActivity extends AppCompatActivity {
    private WebView webview;
    private ProgressBar pb;
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_datails);
        ButterKnife.bind(this);
        pb = (ProgressBar) findViewById(R.id.pb);
        webview = (WebView) findViewById(R.id.webview);

        String url = getIntent().getStringExtra("url");
        String id=getIntent().getStringExtra("id");
        String dif=getIntent().getStringExtra("dif");
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
              //  pb.setVisibility(view.GONE);
            }
        });
        if(url.length()!=0){
            webview.loadUrl(url);
        }else {
            System.out.println("空");
            path = "";
            if(dif.equals("blog")){
                path ="blog_detail?id=";
            }else if(dif.equals("news")){
                path ="news_detail?id=";
            }
            HttpParams params= new HttpParams();
            params.put("id",id);
            System.out.println(id);
            HttpLoader.getInstance(this).get(Constant.PATH+ path +id,params, null, 0x41, new HttpLoader.HttpListener<String>(){

                @Override
                public void onGetResponseSuccess(int requestCode, String response) {
                    if(path.equals("news_detail?id=")){
                        NewsDetail news=XmlUtils.toBean(NewsDetail.class,response.getBytes());
                        String url=news.getNews().getUrl();
                        System.out.println(url);
                        webview.loadUrl(url);
                    }else if(path.equals("blog_detail?id=")){
                        System.out.println("blog_detail?id=");
                        BlogDetail blog=XmlUtils.toBean(BlogDetail.class,response.getBytes());
                        String url=blog.getBlog().getUrl();
                        webview.loadUrl(url);
                    }
                }

                @Override
                public void onGetResponseError(int requestCode, VolleyError error) {

                }
            });

        }

    }
    @OnClick(R.id.iv_arrow)
    public void onViewClicked() {
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

}
