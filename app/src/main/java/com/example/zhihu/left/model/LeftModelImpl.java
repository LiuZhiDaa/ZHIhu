package com.example.zhihu.left.model;

import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.LeftEntity;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/4/19.
 */
public class LeftModelImpl implements ILeftModel {
    @Override
    public void getleft(final DataListener dataListener) {
        RetrofitUtil.initDate().getLeftThemes().enqueue(new retrofit.Callback<LeftEntity>() {
            @Override
            public void onResponse(retrofit.Response<LeftEntity> response, Retrofit retrofit) {
                LeftEntity leftEntity=response.body();
                dataListener.onSuccess(leftEntity);
            }
            @Override
            public void onFailure(Throwable t) {
                dataListener.onSuccess(t);
            }
        });
    }
}
