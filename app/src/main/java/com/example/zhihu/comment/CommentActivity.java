package com.example.zhihu.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.zhihu.R;
import com.example.zhihu.adapter.CommentAdapter;
import com.example.zhihu.base.BaseActivity;
import com.example.zhihu.comment.presenter.CommentPresenterImpl;
import com.example.zhihu.comment.presenter.ICommentPresenter;
import com.example.zhihu.comment.view.ICommentView;
import com.example.zhihu.bean.CommentEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 44744 on 2016/4/28.
 */
public class CommentActivity extends BaseActivity implements ICommentView {
    @Bind(R.id.activity_comment_detail_toolbar)
    Toolbar activityCommentDetailToolbar;
    @Bind(R.id.activity_comment_detail_recyclerView)
    RecyclerView activityCommentDetailRecyclerView;
    private CommentAdapter commentAdapter;
    private String article_id;
    private ICommentPresenter iCommentPresenter;
    private String long_comments,short_comments;
    int toolBarHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        setSupportActionBar(activityCommentDetailToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        activityCommentDetailToolbar.setTitle(" 条点评");
        activityCommentDetailToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        //设置自定义标题之前，先隐藏默认的标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //设置点击事件
        activityCommentDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);//设置排版
        activityCommentDetailRecyclerView.setLayoutManager(linearLayoutManager);
        //
        Intent intent = getIntent();
        article_id = intent.getStringExtra("New_id");
        this.long_comments = intent.getStringExtra("long_comments");
        this.short_comments = intent.getStringExtra("short_comments");
        int sum = Integer.valueOf(this.long_comments) + Integer.valueOf(this.short_comments);
        activityCommentDetailToolbar.setTitle(sum + " 条点评");
        activityCommentDetailToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));//设置标题文字为白色
        //
        //
        iCommentPresenter=new CommentPresenterImpl(this,this);
        iCommentPresenter.getlongComment(Integer.valueOf(article_id));//获取短评论


        activityCommentDetailToolbar.post(new Runnable() {
            @Override
            public void run() {
                toolBarHeight = activityCommentDetailToolbar.getHeight();
                Log.e("toolBarHeight", toolBarHeight + "");
            }
        });


    }
    //获取短评论的bean
    @Override
    public void getShortCommentBean(CommentEntity commentEntity) {
        commentAdapter.setmBean(commentEntity);
        commentAdapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = (LinearLayoutManager) activityCommentDetailRecyclerView.getLayoutManager();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        View view = layoutManager.findViewByPosition(lastVisibleItemPosition);
        int longCommentHeigth = view.getMeasuredHeight();
        int offer = activityCommentDetailRecyclerView.getHeight() -
                toolBarHeight  + longCommentHeigth;
        layoutManager.scrollToPositionWithOffset(lastVisibleItemPosition,
                -(activityCommentDetailRecyclerView.getHeight() - offer));

    }
    //获取长评论的bean
    @Override
    public void getLongCommentBean(CommentEntity commentEntity) {
        commentAdapter=new CommentAdapter(this, commentEntity, this.long_comments, this.short_comments, this);
        activityCommentDetailRecyclerView.setAdapter(commentAdapter);

    }
    @Override
    public void ShowShortCommentClick() {
        iCommentPresenter.getshortComment(Integer.valueOf(article_id));


    }
}
