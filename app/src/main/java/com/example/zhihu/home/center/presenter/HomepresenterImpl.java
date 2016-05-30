package com.example.zhihu.home.center.presenter;

import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.HomeEntity;
import com.example.zhihu.home.center.model.HomepModelImpl;
import com.example.zhihu.home.center.model.IHomeModel;
import com.example.zhihu.home.center.view.IHomeview;

/**
 * Created by 44744 on 2016/4/20.
 */
public class HomepresenterImpl implements Ihomepresenter,DataListener{
    IHomeview iHomeview;
    IHomeModel iHomeModel;
    public HomepresenterImpl( IHomeview iHomeview){
        this.iHomeview=iHomeview;
        iHomeModel=new HomepModelImpl();
    }

    @Override
    public void gethome() {
        iHomeModel.gethomeModel(this);

    }

    @Override
    public void onSuccess(Object o) {
        iHomeview.ihomeview((HomeEntity)o);

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
