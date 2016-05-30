package com.example.zhihu.Theme;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhihu.News.NewsActivity;
import com.example.zhihu.R;
import com.example.zhihu.Theme.presenter.ThempresenterImpl;
import com.example.zhihu.Theme.view.IThemView;
import com.example.zhihu.adapter.ThemeAdapter;
import com.example.zhihu.bean.ThemeEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 44744 on 2016/4/25.
 */
public class ThemeFragment extends Fragment implements IThemView {

    @Bind(R.id.theme_recycle)
    RecyclerView themeRecycle;
    ThempresenterImpl thempresenterImpl;
    ThemeAdapter themeAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        id = getIntent().getExtras().getInt("id");
        int menuID = getArguments().getInt("menuID");

        thempresenterImpl = new ThempresenterImpl(this);
        thempresenterImpl.getThem(menuID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_theme, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void getThem(ThemeEntity themeEntity) {
        themeRecycle.setLayoutManager(new LinearLayoutManager(null));
        Log.i("lhd", "getThem: "+themeEntity.getName());
        themeAdapter = new ThemeAdapter(themeEntity.getStories(), getActivity(), themeEntity);
        themeRecycle.setAdapter(themeAdapter);


        themeAdapter.setOnItemClickListener(new ThemeAdapter.onItemClickListener() {
            @Override
            public void onItemclick(View view, int position) {
                Bundle bundle=new Bundle();
                int id = themeAdapter.getID(position-1);
                bundle.putString("New_id",String.valueOf(id) );
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
