package com.example.zhihu.left.presenter;

import com.example.zhihu.constant.DataListener;
import com.example.zhihu.bean.LeftEntity;
import com.example.zhihu.left.model.ILeftModel;
import com.example.zhihu.left.model.LeftModelImpl;
import com.example.zhihu.left.view.Ileftview;

/**
 * Created by 44744 on 2016/4/19.
 */
public class LeftpresenterImpl implements Ileftpresenter,DataListener {
    Ileftview ileftview;
    ILeftModel iLeftModel;
    public LeftpresenterImpl( Ileftview ileftview){
        this.ileftview=ileftview;
        iLeftModel=new LeftModelImpl();
    }
    @Override
    public void getleft() {
        iLeftModel.getleft(this);

    }

    @Override
    public void onSuccess(Object o) {
        ileftview.Ileftview((LeftEntity) o);

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
