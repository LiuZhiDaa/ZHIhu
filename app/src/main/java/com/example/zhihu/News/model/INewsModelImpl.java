package com.example.zhihu.News.model;

import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.CommentNumber;
import com.example.zhihu.bean.NewsEntity;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/4/25.
 */
public class INewsModelImpl implements INewsModel {
    @Override
    public void getNews(int id, final DataListener dataListener) {
        RetrofitUtil.initDate().getNewsMsg(id).enqueue(new Callback<NewsEntity>() {
            @Override
            public void onResponse(Response<NewsEntity> response, Retrofit retrofit) {
                NewsEntity newsEntity=response.body();
                //新闻
                dataListener.onSuccess(newsEntity);
            }

            @Override
            public void onFailure(Throwable t) {
                dataListener.onError(t);

            }
        });
    }

}
