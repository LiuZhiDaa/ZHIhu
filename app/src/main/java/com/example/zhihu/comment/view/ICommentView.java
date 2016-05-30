package com.example.zhihu.comment.view;

import com.example.zhihu.bean.CommentEntity;

/**
 * Created by 44744 on 2016/4/28.
 */
public interface ICommentView {
    void getLongCommentBean(CommentEntity commentEntity);
    void getShortCommentBean(CommentEntity commentEntity);
    void ShowShortCommentClick();
}
