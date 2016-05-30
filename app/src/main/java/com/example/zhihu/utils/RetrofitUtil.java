package com.example.zhihu.utils;



import com.example.zhihu.constant.InetPro;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by lc on 2016/4/14.
 */
public class RetrofitUtil {

   private static Retrofit retrofit;
   private static InetPro inetPro;

    public static InetPro initDate(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        inetPro = retrofit.create(InetPro.class);
        return inetPro;

    }



}
