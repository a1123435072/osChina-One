package com.itheima.oschina;

import android.app.Application;
import android.content.Context;

/**
 * Created by fly on 2017/3/1.
 */

public class OschinaApplication extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

    }

    public static Context getContext(){
        return mContext;
    }
}
