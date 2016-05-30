package com.example.zhihu.Theme.model;

import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.ThemeEntity;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/4/21.
 */
public class IThemModelImpl implements IThemModel {
    @Override
    public void getThem(final int id,final DataListener dataListener) {
        RetrofitUtil.initDate().getThemeMsg(id).enqueue(new retrofit.Callback<ThemeEntity>() {
            @Override
            public void onResponse(Response<ThemeEntity> response, Retrofit retrofit) {
                ThemeEntity themeEntity=response.body();
                dataListener.onSuccess(themeEntity);
            }

            @Override
            public void onFailure(Throwable t) {
                dataListener.onError(t);

            }
        });
    }
}
