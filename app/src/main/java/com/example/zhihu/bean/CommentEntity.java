package com.example.zhihu.bean;

import java.util.List;

/**
 * Created by 44744 on 2016/4/28.
 */
public class CommentEntity {


    /**
     * author : 天棒
     * content : 嗯嗯，还是我们好，土地都是国家的，国家是人民的，所以土地是人民的ԅ( ¯་། ¯ԅ)
     * avatar : http://pic2.zhimg.com/2390e08a56f12bbb443d8073f2484901_im.jpg
     * time : 1461900521
     * id : 24656149
     * likes : 0
     */

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        private boolean type;
        private String author;
        private String content;
        private String avatar;
        private int time;
        private int id;
        private int likes;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }
    }
}
