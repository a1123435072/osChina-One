package com.itheima.oschina.adapter.sub;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

/**
 * Created by jason on 2017/6/23.
 */

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private List<ImageView> pictureList;

    public ImageAdapter(List<ImageView> pictureList, Context context) {
        this.pictureList = pictureList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pictureList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = pictureList.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
