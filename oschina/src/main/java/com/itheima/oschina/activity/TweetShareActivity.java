package com.itheima.oschina.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.itheima.oschina.R;
import com.itheima.oschina.bean.LoginUserBean;
import com.itheima.oschina.bean.Result;
import com.itheima.oschina.bean.User;
import com.itheima.oschina.xutil.XmlUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

public class TweetShareActivity extends AppCompatActivity {

    EditText et_share;
    TextView tv_cancel;
    TextView tv_send;
    private String share;
    String token;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_share);
        et_share = (EditText) findViewById(R.id.et_share);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_send = (TextView) findViewById(R.id.tv_send);


        //取消点击事件
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //发送点击事件
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share = et_share.getText().toString();

                if (!TextUtils.isEmpty(share)) {
                    String url = "http://www.oschina.net/action/apiv2/resource_image";
                    HttpParams params = new HttpParams();
                    String file_path = Environment.getExternalStorageDirectory().getPath()+"/bear.png";
                                        //  /storage/sdcard
                    params.put("resource",new File(file_path));

                    //先用图片地址resource和cookie访问服务器，返回token
                    HttpHeaders headers = new HttpHeaders();
                    headers.put("cookie",CookieManager.getCookie(TweetShareActivity.this));
                    //这里要post请求方式
                    HttpLoader.getInstance(TweetShareActivity.this).post(url, params, headers, 0x23, new HttpLoader.HttpListener<String>() {

                        @Override
                        public void onGetResponseSuccess(int requestCode, String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject result = (JSONObject) jsonObject.get("result");
                                String token = (String) result.get("token");

                                String url2 = "http://www.oschina.net/action/apiv2/tweet";
                                HttpParams params2 = new HttpParams();
                                params2.put("content",share);
                                params2.put("images",token);
                                //这里再把token和文字内容向服务器请求，进行发布，至于返回什么值，好像没所谓了
                                HttpHeaders headers = new HttpHeaders();
                                headers.put("cookie",CookieManager.getCookie(TweetShareActivity.this));
                                //这里要post请求方式
                                HttpLoader.getInstance(TweetShareActivity.this).post(url2, params2, headers, 0x24, new HttpLoader.HttpListener<String>() {

                                    @Override
                                    public void onGetResponseSuccess(int requestCode, String response) {
                                        System.out.println("--------------------"+response);
                                    }

                                    @Override
                                    public void onGetResponseError(int requestCode, VolleyError error) {

                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onGetResponseError(int requestCode, VolleyError error) {

                        }
                    });
                } else {
                    Toast.makeText(TweetShareActivity.this, "发布内容为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
