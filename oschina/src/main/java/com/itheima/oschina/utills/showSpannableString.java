package com.itheima.oschina.utills;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import static com.itheima.oschina.xutil.UIUtils.getResources;

/**
 * Created by jason on 2017/6/28.
 */

public class showSpannableString {
    public static SpannableString showTextWithImage(String text, int imageRes) {
        SpannableString ss = new SpannableString(text);
        Drawable drawable = getResources().getDrawable(imageRes);
        //设置边界
//		drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, 20, 20);
        ImageSpan span = new ImageSpan(drawable);
        int start = text.indexOf("[");
        int end = text.indexOf("]") + 1;
        ss.setSpan(span, start, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss;
    }

}
