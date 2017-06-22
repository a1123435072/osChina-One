package com.itheima.oschina.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.oschina.R;

/**
 * Created by yangg on 2017/6/22.
 */

public class findItemView extends RelativeLayout {

    public static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";

    private ImageView ivImage;
    private TextView tvTitle;
    private ImageView ivGotoRight;

    public findItemView(Context context) {
        this(context,null);
    }

    public findItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public findItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化布局
        initView();

        //初始化文字和图片的显示
        initAttrs(attrs);
    }

    /**
     * @param attrs  属性集合
     *                 获取在布局文件中定义出来的额自定义的额属性值,
     *                 用于初始化属性值,用于控制文本的显示,和图片的显示
     */
    private void initAttrs(AttributeSet attrs) {
               //获取自定义属性中 ,类型为

         String title = attrs.getAttributeValue(NAMESPACE, "title");

        tvTitle.setText(title);
//        ImageView icon = attrs.getAttributeNameResource(NAMESPACE, "icon");


    }

    //初始化试图
    private void initView() {
        View view = View.inflate(getContext(), R.layout.setting_item_view, null);
        addView(view);
        ivImage = (ImageView) view.findViewById(R.id.iv_setting_icon);
        tvTitle = (TextView) view.findViewById(R.id.tv_setting_title);
        ivGotoRight = (ImageView) view.findViewById(R.id.iv_setting_right);

    }
}
