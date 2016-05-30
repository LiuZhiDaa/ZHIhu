package com.example.zhihu.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by a7945 on 2016/4/28 0028.
 */
public class TimeTransition {

    /**
     * 时间戳10位转时间
     *
     * @param time
     * @return
     */
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
