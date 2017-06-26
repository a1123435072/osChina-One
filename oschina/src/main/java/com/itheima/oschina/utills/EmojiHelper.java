package com.itheima.oschina.utills;

import android.content.Context;

import com.itheima.oschina.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ywf on 2017/2/21.
 */
public class EmojiHelper {


    public static Map<String, Integer>getEmojiMap(){
        Map<String, Integer>  emojiMap = new HashMap<>();
        emojiMap.put("[微笑]",  R.mipmap.smiley_0);
        emojiMap.put("[撇嘴]",  R.mipmap.smiley_1);
        emojiMap.put("[色]",  R.mipmap.smiley_2);
        emojiMap.put("[发呆]",  R.mipmap.smiley_3);
        emojiMap.put("[得意]",  R.mipmap.smiley_4);
        emojiMap.put("[流泪]",  R.mipmap.smiley_5);
        emojiMap.put("[害羞]", R.mipmap.smiley_6);


        emojiMap.put(":bowtie:",  R.mipmap.bowtie);
        emojiMap.put(":smile:",  R.mipmap.smile);
        emojiMap.put(":laughing:",  R.mipmap.laughing);
        emojiMap.put(":blush:",  R.mipmap.blush);
        emojiMap.put(":smiley:",  R.mipmap.smiley);

        return emojiMap;
    }

}
