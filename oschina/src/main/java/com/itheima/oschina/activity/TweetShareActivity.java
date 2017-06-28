package com.itheima.oschina.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

import static android.R.attr.data;

public class TweetShareActivity extends AppCompatActivity {

    EditText et_share;
    TextView tv_cancel;
    TextView tv_send;
    private String share;
    String token;
    List<String> list = new ArrayList<>();

    ImageView iv_image;
    ImageView iv_at;
    ImageView iv_jinhao;
    ImageView iv_emoji;
    ImageView iv_showImage;

    final Object[] objs = new Object[1];//通过Object数组的方式，实现内部类向外部传递数据
    //0是图片路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_share);
        et_share = (EditText) findViewById(R.id.et_share);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_send = (TextView) findViewById(R.id.tv_send);

        iv_image = (ImageView) findViewById(R.id.iv_image);
        iv_at = (ImageView) findViewById(R.id.iv_at);
        iv_jinhao = (ImageView) findViewById(R.id.iv_jinhao);
        iv_emoji = (ImageView) findViewById(R.id.iv_emoji);
        iv_showImage = (ImageView) findViewById(R.id.iv_showImage);


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
                System.out.println("--------------------"+objs[0]);

                if (!TextUtils.isEmpty(share)) {
                    if (!TextUtils.isEmpty((String)objs[0])) {//有图片的发布
                        String url = "http://www.oschina.net/action/apiv2/resource_image";
                        HttpParams params = new HttpParams();
//                    String file_path = Environment.getExternalStorageDirectory().getPath() + "/cup.png";
                        //  /storage/sdcard
                        //通过Object数组的方式，实现内部类向外部传递数据
                        String file_path = (String) objs[0];
                        params.put("resource", new File(file_path));
                        //先用图片地址resource和cookie访问服务器，返回token
                        HttpHeaders headers = new HttpHeaders();
                        headers.put("cookie", CookieManager.getCookie(TweetShareActivity.this));
                        //这里要post请求方式
                        HttpLoader.getInstance(TweetShareActivity.this).post(url, params, headers, 0x23, new HttpLoader.HttpListener<String>() {

                            @Override
                            public void onGetResponseSuccess(int requestCode, String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject result = (JSONObject) jsonObject.get("result");
                                    String token = (String) result.get("token");
                                    publish(share,token);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onGetResponseError(int requestCode, VolleyError error) {

                            }
                        });

                    }else{//没有图片，纯文字的发布
                        publish(share,"");

                    }
                } else {
                    Toast.makeText(TweetShareActivity.this, "发布内容为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        if (1 == requestCode && Activity.RESULT_OK == resultCode && null != data) {
            Uri selectImageUri = data.getData();
            String[] filePathColumn = new String[]{MediaStore.Images.Media.DATA};//要查询的列
            Cursor cursor = getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
            String pirPath = null;
            while (cursor.moveToNext()) {
                pirPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));//所选择的图片路径
            }
//            System.out.println(pirPath);//这个打印的路径就是/storage/sdcard/bear.png  屌不屌

            objs[0] = pirPath;
            //ImageView控件上加载图片
            Bitmap bm = BitmapFactory.decodeFile(pirPath);
            iv_showImage.setImageBitmap(bm);
            cursor.close();
        }
    }


    public void publish (String share,String token){
        String url = "http://www.oschina.net/action/apiv2/tweet";
        HttpParams params = new HttpParams();
        params.put("content", share);
        params.put("images", token);
        //这里再把token和文字内容向服务器请求，进行发布，至于返回什么值，好像没所谓了
        HttpHeaders headers = new HttpHeaders();
        headers.put("cookie", CookieManager.getCookie(TweetShareActivity.this));
        //这里要post请求方式
        HttpLoader.getInstance(TweetShareActivity.this).post(url, params, headers, 0x24, new HttpLoader.HttpListener<String>() {

            @Override
            public void onGetResponseSuccess(int requestCode, String response) {
                Toast.makeText(TweetShareActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });

    }
}
