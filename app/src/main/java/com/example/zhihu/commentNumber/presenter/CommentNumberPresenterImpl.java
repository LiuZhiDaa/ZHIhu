package com.example.zhihu.commentNumber.presenter;

import com.example.zhihu.bean.CommentNumber;
import com.example.zhihu.commentNumber.model.CommentNumberModelImpl;
import com.example.zhihu.commentNumber.model.ICommentNumberModel;
import com.example.zhihu.commentNumber.view.ICommentNumberView;
import com.example.zhihu.constant.DataListener;

/**
 * Created by 44744 on 2016/4/28.
 */
public class CommentNumberPresenterImpl implements ICommentNumberPresenter ,DataListener{
    ICommentNumberView iCommentNumberView;
    ICommentNumberModel iCommentNumberModel;
    public CommentNumberPresenterImpl(ICommentNumberView iCommentNumberView){
        this.iCommentNumberView=iCommentNumberView;
        iCommentNumberModel= new  CommentNumberModelImpl();
    }
    @Override
    public void getNumber(int id) {
        iCommentNumberModel.getNumber(id,this);
    }

    @Override
    public void onSuccess(Object o) {
        iCommentNumberView.getNumber((CommentNumber) o);
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
