package com.example.zhihu.launcher.presenter;


import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.LauncherEntity;
import com.example.zhihu.launcher.model.ILauncherModel;
import com.example.zhihu.launcher.model.LauncherModelImpl;
import com.example.zhihu.launcher.view.ILauncherview;

/**
 * Created by 44744 on 2016/4/14.
 */
public class LauncherPresenterImpl implements ILauncherpresenter ,DataListener {
    ILauncherview iLauncherview;
    ILauncherModel iLauncherModel;
    public LauncherPresenterImpl(ILauncherview iLauncherview){
        this.iLauncherview=iLauncherview;
        iLauncherModel=new LauncherModelImpl();
    }
    @Override
    public void getILaucherSize(String size) {

        iLauncherModel.getLauncherModel(size,this);


    }


    @Override
    public void onSuccess(Object o) {
        iLauncherview.initILauncher((LauncherEntity) o);
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
