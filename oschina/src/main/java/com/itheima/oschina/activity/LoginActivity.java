package com.itheima.oschina.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.itheima.oschina.R;
import com.itheima.oschina.bean.CommentList;
import com.itheima.oschina.bean.LoginUserBean;
import com.itheima.oschina.bean.Result;
import com.itheima.oschina.bean.User;
import com.itheima.oschina.xutil.XmlUtils;

import org.senydevpkg.net.HttpHeaders;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.utils.CookieManager;
import org.senydevpkg.utils.SPUtil;

import java.io.File;
import java.util.Map;


/**
 * Created by fly on 2017/3/1.
 */

public  class LoginActivity extends AppCompatActivity {
    Context context;

    EditText et_login_username;
    EditText et_login_pwd;
    Button bt_login_submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_username = (EditText) findViewById(R.id.et_login_username);
        et_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        bt_login_submit = (Button) findViewById(R.id.bt_login_submit);

        bt_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_login_username.getText().toString().trim();
                String pwd = et_login_pwd.getText().toString().trim();

                String url = "http://www.oschina.net/action/api/login_validate";
                HttpParams params = new HttpParams();
                params.put("keep_login", "1");
                params.put("username", username);
                params.put("pwd", pwd);

                HttpLoader.getInstance(context).get(url, params, null, 0x22, new HttpLoader.HttpListener<String>() {
                    @Override
                    public void onParaseNetWorkResponse(NetworkResponse networkResponse) {
                        super.onParaseNetWorkResponse(networkResponse);
                        //这个网络数据里面有headers变量，里面包含了Cookie值
                        Map<String,String> headers = networkResponse.headers;
                        //这个save方法，将Cookie保存在sp中，Manager类在老师给的框架中
                        CookieManager.saveCookie(LoginActivity.this,headers);
                    }

                    @Override
                    public void onGetResponseSuccess(int requestCode, String response) {
                        /**
                            在登录的相应结果中，会包含一个用户唯一标识uid
                            此时用户需要把uid持久化到文件中
                         */
                        LoginUserBean loginUserBean = XmlUtils.toBean(LoginUserBean.class, response.getBytes());
                        Result result = loginUserBean.getResult();
                        Toast.makeText(getApplicationContext(), result.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        if(result.getErrorCode()==0){
                            //用户名密码错误

                        }else if(result.getErrorCode()==1){
                            //登录成功
                            //将uid存到sp中，其他界面使用
                            User user = loginUserBean.getUser();
                            SPUtil.newInstance(getApplicationContext()).putString("uid",user.getId()+"");
                            //跳转到发布动弹页面
                            Intent intent = new Intent(context, TweetDetailsActivity.class);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {
                        System.out.println("错误---------");
                    }
                });
            }
        });

    }

    //上传数据
    private void upLoadFile(){
        String url = "";
        HttpParams params = new HttpParams();
        String file_path = Environment.getExternalStorageDirectory().getPath()+"/bear.png";
        params.put("resource",new File(file_path));

        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie",CookieManager.getCookie(this));
        HttpLoader.getInstance(this).get(url, params, null, 0x23, new HttpLoader.HttpListener<String>() {

            @Override
            public void onGetResponseSuccess(int requestCode, String response) {

            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
    }




}
