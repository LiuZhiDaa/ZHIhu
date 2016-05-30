package com.example.zhihu.bean;

/**
 * Created by 44744 on 2016/4/14.
 */
public class LauncherEntity {

    /**
     * text : © 许英龙
     * img : https://pic2.zhimg.com/a46cb6edb7c4566fcecb4138e0d65268.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "LauncherEntity{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
