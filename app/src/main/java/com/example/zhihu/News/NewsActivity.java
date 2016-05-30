package com.example.zhihu.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.zhihu.News.presener.INewsPresenterImpl;
import com.example.zhihu.News.view.INewsView;
import com.example.zhihu.R;
import com.example.zhihu.base.BaseActivity;
import com.example.zhihu.bean.CommentNumber;
import com.example.zhihu.comment.CommentActivity;
import com.example.zhihu.bean.NewsEntity;
import com.example.zhihu.commentNumber.presenter.CommentNumberPresenterImpl;
import com.example.zhihu.commentNumber.view.ICommentNumberView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 44744 on 2016/4/25.
 */
public class NewsActivity extends BaseActivity implements INewsView, View.OnClickListener ,ICommentNumberView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.NEWSBarLayout)
    AppBarLayout NEWSBarLayout;
    @Bind(R.id.NEWS_webview)
    WebView NEWSWebview;
    INewsPresenterImpl iNewsPresenter;
    CommentNumberPresenterImpl commentNumberPresenter;
    NewsEntity newsEntity;
    @Bind(R.id.activity_article_thumb)
    TextView activityArticleThumb;
    @Bind(R.id.activity_article_comment)
    TextView activityArticleComment;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.theme_header_editor)
    RecyclerView themeHeaderEditor;
    private String article_id;//文章的id
    private int long_comments,short_comments;
    String new_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        new_id = intent.getStringExtra("New_id");
        iNewsPresenter = new INewsPresenterImpl(this);
        iNewsPresenter.getNews(Integer.valueOf(new_id));
        Log.i("lhd", "onCreate: "+new_id);
        commentNumberPresenter=new CommentNumberPresenterImpl(this);
        commentNumberPresenter.getNumber(Integer.valueOf(new_id));
        setSupportActionBar(toolbar);
        activityArticleComment.setOnClickListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getNews(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        initweb();
    }

    private void initweb() {
        WebSettings webSettings = NEWSWebview.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//适应屏幕。内容自动缩放
        webSettings.setDisplayZoomControls(false);//设定缩放控件隐藏w
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);// 用于设置webview放大
        webSettings.setBuiltInZoomControls(false);
//        webview.setBackgroundResource(R.color.transparent);
        String htmlbody = " ";

        if (this.newsEntity.getCss() != null && this.newsEntity.getCss().size() != 0) {
            for (int i = 0; i < newsEntity.getCss().size(); i++) {
                htmlbody += "<link rel=\"stylesheet\" href=\"" + this.newsEntity.getCss().get(i) + "\" type=\"text/css\" />" + "</br>";
            }
        }
        htmlbody += this.newsEntity.getBody();

        Document document = Jsoup.parse(htmlbody.trim());
        Elements firstdiv = document.select("div.headline");

        firstdiv.remove();
//            //将所有的图片都设置宽度适应屏幕大小
        Elements imgelement = document.select("img");
        // 设置所有图片的大小
        for (int i = 0; i < imgelement.size(); i++) {

            if (!imgelement.get(i).className().equals("avatar")) {
                imgelement.get(i).attr("width", "100%");
            }


        }

        this.NEWSWebview.loadDataWithBaseURL("", document.html(), "textml",
                "utf-8", null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_article_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("New_id", new_id);
                bundle.putString("long_comments", String.valueOf(this.long_comments));//长评论数
                bundle.putString("short_comments", String.valueOf(this.short_comments));//短评论数
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getNumber(CommentNumber commentNumber) {
        activityArticleThumb.setText(commentNumber.getPopularity()+"");
        activityArticleComment.setText(commentNumber.getComments()+"");
        this.long_comments=commentNumber.getLong_comments();
        this.short_comments=commentNumber.getShort_comments();
        Log.i("lhd", "getNumber: "+commentNumber.getPopularity());
    }
}
