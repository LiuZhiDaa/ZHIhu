package com.example.zhihu.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihu.MainActivity;
import com.example.zhihu.R;
import com.example.zhihu.base.BaseActivity;
import com.example.zhihu.bean.LauncherEntity;
import com.example.zhihu.launcher.presenter.LauncherPresenterImpl;
import com.example.zhihu.launcher.view.ILauncherview;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LauncherActivity extends BaseActivity implements ILauncherview {


    @Bind(R.id.guide_image)
    ImageView guideImage;
    @Bind(R.id.guide_author_name)
    TextView guideAuthorName;
    ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        ButterKnife.bind(this);
        LauncherPresenterImpl iLauncherpresenter = new LauncherPresenterImpl(this);
        iLauncherpresenter.getILaucherSize("1080*1776");
    }

    @Override
    public void initILauncher(LauncherEntity launcherEntity) {
        scaleAnimation=new ScaleAnimation(1f,1.05f,1f,1.03f,0.5f,0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        Glide.with(LauncherActivity.this).load(launcherEntity.getImg()).animate(scaleAnimation).into(guideImage);
        startAnimation();
    }
    private void startAnimation() {
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(mcontext, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
