package com.example.zhihu.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HD on 2016/4/29.
 */
public class DataUtils {
    private static Date LastDay;

    public static String timeCalendarToString(int time) {
        Date SYdate = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(SYdate);
        calendar.add(Calendar.DAY_OF_MONTH, -time);
        LastDay = calendar.getTime();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = sDateFormat.format(LastDay);
        return format;
    }

    public static String timestampToString(Integer time) {
        //int转long时，先进行转型再进行计算，否则会是计算结束后在转型
        long temp = (long) time * 1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        try {
            //方法一
            tsStr = dateFormat.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }
}
