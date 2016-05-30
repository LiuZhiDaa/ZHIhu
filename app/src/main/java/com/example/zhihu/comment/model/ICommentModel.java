package com.example.zhihu.comment.model;

import com.example.zhihu.bean.CommentEntity;
import com.example.zhihu.constant.DataListener;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by 44744 on 2016/4/28.
 */
public interface ICommentModel {
    @GET("/api/4/story/{id}/long-comments")//花括号的id为文章的id
    Call<CommentEntity> getLongComment(@Path("id") int id);

    @GET("/api/4/story/{id}/short-comments")//花括号的id为文章的id
    Call<CommentEntity> getShortComment(@Path("id") int id);
}
