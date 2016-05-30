package com.example.zhihu.News.model;

import com.example.zhihu.constant.DataListener;

/**
 * Created by 44744 on 2016/4/25.
 */
public interface INewsModel {
    void getNews(int id, DataListener dataListener);
}
