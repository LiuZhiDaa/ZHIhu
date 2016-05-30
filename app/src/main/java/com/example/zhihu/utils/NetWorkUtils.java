package com.example.zhihu.utils;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by a7945 on 2016/4/18 0018.
 */
public class NetWorkUtils {
    public static Retrofit InitNetWork(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com")//根地址
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static int getStatusBarHright(Context context) {
        Class c = null;
        int y = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            y = context.getResources().getDimensionPixelSize(x);
            Log.e("TAG", y + "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return y;
    }

}
