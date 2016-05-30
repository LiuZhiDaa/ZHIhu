package com.example.zhihu.commentNumber.model;

import com.example.zhihu.bean.CommentNumber;
import com.example.zhihu.constant.DataListener;
import com.example.zhihu.utils.RetrofitUtil;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by 44744 on 2016/4/28.
 */
public class CommentNumberModelImpl implements ICommentNumberModel {
    @Override
    public void getNumber(int id, final DataListener dataListener) {
        RetrofitUtil.initDate().GetAwesomeCommentNumber(id).enqueue(new Callback<CommentNumber>() {
            @Override
            public void onResponse(Response<CommentNumber> response, Retrofit retrofit) {
                dataListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                dataListener.onError(t);
            }
        });
    }
}
