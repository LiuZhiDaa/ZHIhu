package com.example.zhihu.PassNews.model;

import com.example.zhihu.bean.HomeEntity;
import com.example.zhihu.constant.DataListener;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/5/3.
 */
public class PassNewsModelImpl implements IPassNewsModel {
    @Override
    public void getPassNewsModel(String Date, final DataListener dataListener) {
        RetrofitUtil.initDate().getMoreHomeNews(Date).enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(Response<HomeEntity> response, Retrofit retrofit) {
                HomeEntity homeEntity=response.body();
                dataListener.onSuccess(homeEntity);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
