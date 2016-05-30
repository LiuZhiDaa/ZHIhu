package com.example.zhihu.home.center;


import android.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhihu.News.NewsActivity;
import com.example.zhihu.PassNews.presenter.IPassNewsPresenter;
import com.example.zhihu.PassNews.presenter.PassNewsPresenterImpl;
import com.example.zhihu.PassNews.view.IPassNewsView;
import com.example.zhihu.R;
import com.example.zhihu.bean.HomeEntity;
import com.example.zhihu.home.center.presenter.HomepresenterImpl;
import com.example.zhihu.home.center.presenter.Ihomepresenter;
import com.example.zhihu.home.center.view.IHomeview;
import com.example.zhihu.utils.DataUtils;
import com.example.zhihu.utils.LoopView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 44744 on 2016/4/18.
 */
public class HomeFragment extends Fragment implements IHomeview, SwipeRefreshLayout.OnRefreshListener, IPassNewsView {


    @Bind(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;
    @Bind(R.id.Swipe)
    SwipeRefreshLayout Swipe;
    Ihomepresenter ihomepresenter;
    IPassNewsPresenter iPassNewsPresenter;
    HomeAdapter homeAdapter;
    private boolean refresh_flag = true;
    int i=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        ihomepresenter = new HomepresenterImpl(this);
        iPassNewsPresenter=new PassNewsPresenterImpl(this);

        ihomepresenter.gethome();
        //滑动监听
        homeRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //列表所有条目数
                int totalItemsSize = layoutManager.getItemCount() - 1;//因为头部占了一个位置，但是并不属于新闻列表的范围内，
                //当前屏幕中的条目数
                int visibilityChildSize = layoutManager.getChildCount();
                //当前屏幕中第一个条目位置
                int visibilityChildPosition = layoutManager.findFirstVisibleItemPosition();
                //判断是否到列表底部，如果到底部，则执行方法体内的内容

                if (visibilityChildPosition + visibilityChildSize >= totalItemsSize) {
                    if (refresh_flag) {
                        refresh_flag = false;
                        Toast.makeText(getActivity(), "到底了", Toast.LENGTH_SHORT).show();
                        iPassNewsPresenter.getPassNewsPresenter(DataUtils.timeCalendarToString(i++));//获取总数目减一的数据的id，传给主持层的方法，以便回调View层的接口方法

                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        Swipe.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void ihomeview(HomeEntity homeEntity) {
        homeAdapter = new HomeAdapter(homeEntity, getActivity());
        homeRecyclerview.setAdapter(homeAdapter);
        Swipe.setRefreshing(false);//停止刷新动画

    }

    @Override
    public void onRefresh() {
        ihomepresenter.gethome();
    }

    @Override
    public void ipassNewsView(HomeEntity homeEntity) {
        homeAdapter.AddDisplay(homeEntity);
        refresh_flag = true;
    }

    class HomeAdapter extends RecyclerView.Adapter<Holder>{

        private Context mContext;

        public  List<HomeEntity.StoriesBean> more_mBean = new ArrayList<>();//存储更多新闻bean的链表
        HomeEntity homeEntitym;
        /*  当前是头部**/
        public final int IS_HEAD = 0;
        /* 当前是body **/
        public final int IS_BODY = 1;

        public HomeAdapter(HomeEntity homeEntity, Context mContext) {
            this.mContext = mContext;
            more_mBean.addAll(homeEntity.getStories());
            homeEntitym = homeEntity;
        }
        public void AddDisplay(HomeEntity homeEntity){
            more_mBean.addAll(homeEntity.getStories());
        }
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder viewHolder;
            if (viewType == IS_HEAD) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_head, parent, false);
                viewHolder = new Holder(view, IS_HEAD);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
                viewHolder = new Holder(view, IS_BODY);
            }

            return viewHolder;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return IS_HEAD;
            else
                return IS_BODY;
        }

        @Override
        public void onBindViewHolder(Holder holder, final int position) {
            if (position == 0) {
                holder.loopview.setDatas(homeEntitym.getTop_stories());
            } else {
//                holder.home_page_time.setVisibility(View.VISIBLE);
//                holder.home_page_time.setText("今日热闻");//设置时间
                HomeEntity.StoriesBean storiesBean = more_mBean.get(position-1);
                holder.title.setText(storiesBean.getTitle());
                Glide.with(mContext).load(storiesBean.getImages().get(0)).into(holder.img);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        int id = more_mBean.get(position - 1).getId();
                        bundle.putString("New_id", String.valueOf(id));
                        Intent intent = new Intent(mContext, NewsActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return more_mBean.size();
        }
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        LoopView loopview;

        TextView home_page_time;

        public Holder(View itemView, int type) {
            super(itemView);

            if (type == 0) {
                loopview = (LoopView) itemView.findViewById(R.id.Loopview);

            } else {
//                home_page_time= (TextView) itemView.findViewById(R.id.home_page_time);
                title = (TextView) itemView.findViewById(R.id.tv_title);
                img = (ImageView) itemView.findViewById(R.id.img);
            }
        }
    }
}
