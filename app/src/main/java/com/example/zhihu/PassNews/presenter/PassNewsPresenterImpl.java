package com.example.zhihu.PassNews.presenter;

import com.example.zhihu.PassNews.model.IPassNewsModel;
import com.example.zhihu.PassNews.model.PassNewsModelImpl;
import com.example.zhihu.PassNews.view.IPassNewsView;
import com.example.zhihu.bean.HomeEntity;
import com.example.zhihu.constant.DataListener;

/**
 * Created by 44744 on 2016/5/3.
 */
public class PassNewsPresenterImpl implements IPassNewsPresenter,DataListener {
    IPassNewsView iPassNewsView;
    IPassNewsModel iPassNewsModel;
    public PassNewsPresenterImpl(IPassNewsView iPassNewsView){
        this.iPassNewsView=iPassNewsView;
        iPassNewsModel=new PassNewsModelImpl();
    }
    @Override
    public void onSuccess(Object o) {
        iPassNewsView.ipassNewsView((HomeEntity)o);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void getPassNewsPresenter(String Date) {
        iPassNewsModel.getPassNewsModel(Date,this);
    }
}
