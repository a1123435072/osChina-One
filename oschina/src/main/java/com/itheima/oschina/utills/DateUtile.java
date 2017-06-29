package com.itheima.oschina.utills;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jason on 2017/6/28.
 */

public class DateUtile {
    public static boolean isToday(String date){
        Date today= new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String StringToday = sdf.format(today);
        StringBuilder builder1= new StringBuilder(StringToday);
        String day=builder1.substring(8,10);
        StringBuilder builder2= new StringBuilder(date);
        String dateGetted= builder2.substring(8,10);
        if(day.equals(dateGetted)){
            return true;
        }else {
            return false;
        }
    }
}
