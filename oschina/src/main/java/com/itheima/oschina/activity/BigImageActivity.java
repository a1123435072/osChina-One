package com.itheima.oschina.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.itheima.oschina.R;
import com.squareup.picasso.Picasso;

/**
 * Created by raynwang on 2017/6/29.
 */

public class BigImageActivity extends Activity {
    ImageView iv_imageBig;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        iv_imageBig = (ImageView) findViewById(R.id.iv_imageBig);

        String url = getIntent().getStringExtra("urlImageBig");
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(this).load(url).into(iv_imageBig);
        }

        iv_imageBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
