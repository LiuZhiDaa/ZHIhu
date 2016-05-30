package com.example.zhihu.Theme.presenter;

import com.example.zhihu.Theme.model.IThemModel;
import com.example.zhihu.Theme.model.IThemModelImpl;
import com.example.zhihu.Theme.view.IThemView;
import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.ThemeEntity;

/**
 * Created by 44744 on 2016/4/21.
 */
public class ThempresenterImpl implements IThempresenter,DataListener{
    IThemView iThemView;
    IThemModel iThemModel;
    public  ThempresenterImpl(IThemView iThemView){
        this.iThemView=iThemView;
        iThemModel=new IThemModelImpl();
    }
    @Override
    public void getThem(int id) {
        iThemModel.getThem(id,this);
    }

    @Override
    public void onSuccess(Object o) {
        iThemView.getThem((ThemeEntity)o);
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
