package com.example.zhihu.launcher.model;


import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.LauncherEntity;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/4/14.
 */
public class    LauncherModelImpl implements ILauncherModel {


    @Override
    public void getLauncherModel(String size, final DataListener dataListener) {
        RetrofitUtil.initDate().getLauncherImg(size).enqueue(new Callback<LauncherEntity>() {
            @Override
            public void onResponse(Response<LauncherEntity> response, Retrofit retrofit) {
                dataListener.onSuccess(response.body());

            }

            @Override
            public void onFailure(Throwable t) {
                dataListener.onSuccess(t);

            }
        });

    }
}
