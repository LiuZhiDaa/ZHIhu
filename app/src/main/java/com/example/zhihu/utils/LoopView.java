package com.example.zhihu.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.zhihu.bean.HomeEntity;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by GavinHua on 2016/1/29.
 */
public class LoopView extends ViewPager {
    Context context;

    public LoopView(Context context) {
        super(context);
        this.context = context;
    }

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    boolean flag;
    List<ImageView> views = new ArrayList<>();
//    View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_item_head,"",false);

    public void setDatas(List<HomeEntity.TopStoriesBean> datas) {

        for (HomeEntity.TopStoriesBean data : datas) {
            ImageView imageView = new ImageView(context);
            TextView textView = new TextView(context);
            //设置轮播图占满
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
             Glide.with(context).load(data.getImage()).into(imageView);
//            textView.setText(data.getTitle());
//            textView.setTextSize(23);

            views.add(imageView);

        }

        setAdapter(new LoopAdapter());
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                KLog.d(state);
                if (state == 1) flag = true;
                if (state == 0) flag = false;
            }
        });
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!flag) {
                    int currentItem = getCurrentItem();
                    currentItem++;
                    if (currentItem == views.size()) currentItem = 0;


                    Message msg = new Message();
                    msg.what = 0;
                    Bundle bundle = new Bundle();
                    bundle.putInt("item", currentItem);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }

            }
        };
        new Timer().schedule(timerTask, 3000, 3000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int item = msg.getData().getInt("item");
                setCurrentItem(item);
            }
        }
    };


    class LoopAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = views.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView imageView = views.get(position);
            container.removeView(imageView);
        }
    }

}
