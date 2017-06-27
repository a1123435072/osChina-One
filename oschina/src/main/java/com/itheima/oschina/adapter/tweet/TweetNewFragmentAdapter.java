package com.itheima.oschina.adapter.tweet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.oschina.R;
import com.itheima.oschina.activity.MainActivity;
import com.itheima.oschina.activity.TweetDetailsActivity;
import com.itheima.oschina.bean.Tweet;
import com.itheima.oschina.utills.SpannableUtil;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.droidlover.xrichtext.ImageLoader;
import cn.droidlover.xrichtext.XRichText;

import static android.provider.Telephony.Mms.Part.TEXT;
import static com.itheima.oschina.xutil.UIUtils.getContext;
import static com.itheima.oschina.xutil.UIUtils.getResources;

/**
 * Created by raynwang on 2017/6/22.
 */

public class TweetNewFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;
    Context context;

    private List<Tweet> items = new ArrayList<>();

    XRichText richText = new XRichText(getContext());

    public TweetNewFragmentAdapter(Activity activity, Context context,List<Tweet> items) {
        this.mActivity = activity;
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_tweet_new_fragment_item, parent, false);
                                                                        //最新动弹item
        return new TweetNewFragmentAdapter.TweetNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final TweetNewFragmentAdapter.TweetNewViewHolder tweetNewViewHolder = (TweetNewFragmentAdapter.TweetNewViewHolder) holder;

//        richText.callback(new XRichText.BaseClickCallback() {
//
//                    @Override
//                    public boolean onLinkClick(String url) {
//                        showMsg(url);
//                        return true;
//                    }
//
//                    @Override
//                    public void onImageClick(List<String> urlList, int position) {
//                        super.onImageClick(urlList, position);
//                        showMsg("图片:" + position);
//                    }
//
//                    @Override
//                    public void onFix(XRichText.ImageHolder holder) {
//                        super.onFix(holder);
//                        if (holder.getPosition() % 3 == 0) {
//                            holder.setStyle(XRichText.Style.LEFT);
//                        } else if (holder.getPosition() % 3 == 1) {
//                            holder.setStyle(XRichText.Style.CENTER);
//                        } else {
//                            holder.setStyle(XRichText.Style.RIGHT);
//                        }
//
//                        //设置宽高
//                        holder.setWidth(550);
//                        holder.setHeight(400);
//                    }
//                })
//                .imageDownloader(new ImageLoader() {
//                    @Override
//                    public Bitmap getBitmap(String url) throws IOException {
//                        return UILKit.getLoader().loadImageSync(url);
//                    }
//                })
//                .text(TEXT);





        tweetNewViewHolder.tv_content.setText(items.get(position).getBody());
        tweetNewViewHolder.tv_id.setText(items.get(position).getAuthor());
        tweetNewViewHolder.tv_time.setText(items.get(position).getPubDate());
        tweetNewViewHolder.tv_commemntNumber.setText(items.get(position).getCommentCount());
        tweetNewViewHolder.tv_likeNumber.setText(items.get(position).getLikeCount()+"");

        ImageView imageView = tweetNewViewHolder.iv_head;
//        BitmapUtils.display(context,imageView,items.get(position).getPortrait());

        String urlPortrait = items.get(position).getPortrait();
        if (!TextUtils.isEmpty(urlPortrait)) {
            Picasso.with(context).load(urlPortrait).into(imageView);
        }

        //富文本
//        String content = items.get(position).getBody();
//        tweetNewViewHolder.tv_content.setText(content);
//        Spannable spannable = SpannableUtil.formatterOnlyTag(context, tweetNewViewHolder.tv_content.getText());
//        spannable = SpannableUtil.formatterOnlyLink(context, spannable);
//        spannable = SpannableUtil.formatterEmoji(getResources(), spannable, 50);//先不弄表情
//        tweetNewViewHolder.tv_content.setMovementMethod(LinkMovementMethod.getInstance());
//        tweetNewViewHolder.tv_content.setText(spannable);


//        content_txt_6.setText("xxxxxxx<a href='http://www.baidu.com'>www.baidu.com</a>");

//        SpannableStringBuilder builder = new SpannableStringBuilder(content);
//
//        Pattern pattern = Pattern.compile(
//                "<a\\s+href=['\"]([^'\"]*)['\"][^<>]*>([^<>]*)</a>"
//        );
//
//        Matcher matcher;
//        while (true) {
//            matcher = pattern.matcher(builder.toString());
//            if (matcher.find()) {
//                final String group0 = matcher.group(1);//http://www.baidu.com
//                final String group1 = matcher.group(2);//www.baidu.com
//
//                // 字符串中<a href='http://www.baidu.com'>www.baidu.com</a> 替换成 www.baidu.com
//                builder.replace(matcher.start(), matcher.end(), group1);
//                ClickableSpan span = new ClickableSpan() {
//                    @Override
//                    public void onClick(View widget) {
//                        Toast.makeText(getContext(), "group0 = " + group0, Toast.LENGTH_SHORT).show();
//                    }
//                };
//                builder.setSpan(span, matcher.start(), matcher.start() + group1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                continue;
//            }
//            break;
//        }
//        //设置TextView的行为.比如:超链接行为  或者  滑动行为
//        tweetNewViewHolder.tv_content.setMovementMethod(LinkMovementMethod.getInstance());// ScrollingMovementMethod.getInstance();
//        //将格式化的字符串,设置给textview
//        tweetNewViewHolder.tv_content.setText(builder);



        tweetNewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TweetDetailsActivity.class);
                int id = items.get(position).getId();
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    public void addAll(List<Tweet> datas) {
//        items.addAll(datas);
//        notifyItemRangeInserted(items.size() - 1, getItemCount() + datas.size());
//        //需要刷新一次，因为加载新的item时，itemCount需要相应变化
//        notifyDataSetChanged();
//    }

    //在哪用的？？？
    public void clear() {
//        notifyItemRangeRemoved(1, getItemCount());
        items.clear();
    }

    class TweetNewViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_content;
        private final TextView tv_id;
        private final TextView tv_time;
        private final ImageView iv_head;
        private final TextView tv_likeNumber;
        private final TextView tv_commemntNumber;

        public TweetNewViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_likeNumber = (TextView) itemView.findViewById(R.id.tv_likeNumber);
            tv_commemntNumber = (TextView) itemView.findViewById(R.id.tv_commentNumber);
        }

    }
}
