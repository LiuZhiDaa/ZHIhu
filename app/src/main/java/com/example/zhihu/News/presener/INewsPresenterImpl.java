package com.example.zhihu.News.presener;

import com.example.zhihu.News.model.INewsModel;
import com.example.zhihu.News.model.INewsModelImpl;
import com.example.zhihu.News.view.INewsView;
import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.CommentNumber;
import com.example.zhihu.bean.NewsEntity;

/**
 * Created by 44744 on 2016/4/25.
 */
public class INewsPresenterImpl implements INewsPresenter,DataListener {
    INewsView iNewsView;
    INewsModel iNewsModel;
    public INewsPresenterImpl(INewsView iNewsView){
        this.iNewsView=iNewsView;
        iNewsModel=new INewsModelImpl();
    }
    @Override
    public void onSuccess(Object o) {
        iNewsView.getNews((NewsEntity)o);
    }

    @Override
    public void onError(Throwable throwable) {

    }
    //
    @Override
    public void getNews(int id) {
        iNewsModel.getNews(id,this);

    }

}
