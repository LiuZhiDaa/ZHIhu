package com.example.zhihu.constant;

import com.example.zhihu.bean.CommentEntity;
import com.example.zhihu.bean.CommentNumber;
import com.example.zhihu.bean.HomeEntity;
import com.example.zhihu.bean.LauncherEntity;
import com.example.zhihu.bean.LeftEntity;
import com.example.zhihu.bean.NewsEntity;
import com.example.zhihu.bean.ThemeEntity;
import com.example.zhihu.comment.model.ICommentModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by lc on 2016/4/14.
 */
public interface InetPro {
    //这就是利用注解在你用GET方式请求的时候会自动给你补全一部分URL路径，花括号里面的有user点类似变量对应下面参数
    @GET("api/4/start-image/{size}")
    //这个注解的大概理解为传过来的的String参数会添加到上面的花括号里
    Call<LauncherEntity> getLauncherImg(@Path("size") String size);
    /**
     * 获取主题列表
     *
     * @return
     */
    @GET("/api/4/themes")
    Call<LeftEntity> getLeftThemes();
    /*
    获取主页信息
     */
    @GET("api/4/news/latest")
    Call<HomeEntity> getHomeMsg();
    /*
    主题日报内容查看
     */
    @GET("api/4/theme/{id}")
    Call<ThemeEntity>getThemeMsg(@Path("id")  int id);

    /*
    消息内容获取与离线下载
     */
    @GET("api/4/news/{id}")
    Call<NewsEntity>getNewsMsg(@Path("id") int id);
    //获取评论、点赞数的请求
    @GET("api/4/story-extra/{id}")
    Call<CommentNumber> GetAwesomeCommentNumber(@Path("id") int id);

    /*
    长评论
     */
    @GET("api/4/story/{id}/long-comments")
    Call<CommentEntity>getCommentLongMsg(@Path("id") int id);
    /*
    短评论
     */
    @GET("api/4/story/{id}/short-comments")
    Call<CommentEntity>getCommentShortMsg(@Path("id") int id);
    /*
    获取更多新闻
     */
    @GET("api/4/news/before/{Date}")
    Call<HomeEntity> getMoreHomeNews(@Path("Date") String Date);//因为这个网络获取，后面不需要拼接地址，所以形参不需要传值。


}
