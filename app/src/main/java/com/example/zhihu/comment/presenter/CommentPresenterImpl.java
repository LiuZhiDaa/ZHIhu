package com.example.zhihu.comment.presenter;

import android.content.Context;

import com.example.zhihu.bean.CommentEntity;
import com.example.zhihu.comment.model.ICommentModel;
import com.example.zhihu.comment.view.ICommentView;
import com.example.zhihu.constant.InetPro;
import com.example.zhihu.utils.NetWorkUtils;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/4/28.
 */
public class CommentPresenterImpl implements ICommentPresenter {
    ICommentView iCommentView;
    private Context mContext;
    public CommentPresenterImpl(ICommentView iCommentView,Context context){
        this.iCommentView=iCommentView;
        this.mContext = context;
    }
    @Override
    public void getlongComment(int id) {
        Retrofit retrofit = NetWorkUtils.InitNetWork();
        ICommentModel commentDetailService = retrofit.create(ICommentModel.class);
        Call<CommentEntity> call = commentDetailService.getLongComment(id);
        call.enqueue(new Callback<CommentEntity>() {
            @Override
            public void onResponse(Response<CommentEntity> response, Retrofit retrofit) {
                iCommentView.getLongCommentBean(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    @Override
    public void getshortComment(int id) {
        Retrofit retrofit = NetWorkUtils.InitNetWork();
        ICommentModel commentDetailService = retrofit.create(ICommentModel.class);
        Call<CommentEntity> call = commentDetailService.getShortComment(id);
        call.enqueue(new Callback<CommentEntity>() {
            @Override
            public void onResponse(Response<CommentEntity> response, Retrofit retrofit) {
                iCommentView.getShortCommentBean(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


}
