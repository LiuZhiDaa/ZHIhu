package com.example.zhihu.home.center.model;

import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.HomeEntity;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/4/20.
 */
public class HomepModelImpl implements IHomeModel {
    @Override
    public void gethomeModel(final DataListener dataListener) {
        RetrofitUtil.initDate().getHomeMsg().enqueue(new retrofit.Callback<HomeEntity>() {
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
