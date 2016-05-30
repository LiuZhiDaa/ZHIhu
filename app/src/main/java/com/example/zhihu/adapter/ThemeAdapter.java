package com.example.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihu.R;
import com.example.zhihu.bean.ThemeEntity;

import java.util.List;

/**
 * Created by 44744 on 2016/4/21.
 */
public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.MyViewHolder> {
    List<ThemeEntity.StoriesBean> mThemeEntity;
    Context context;
    ThemeEntity mmThemeEntity;
    /*  当前是头部**/
    public final int IS_HEAD = 0;
    /* 当前是body **/
    public final int IS_BODY = 1;
    public ThemeAdapter(List<ThemeEntity.StoriesBean> themesEntities, Context mContext, ThemeEntity themeEntity) {
        this.mThemeEntity = themesEntities;
        this.context = mContext;
        this.mmThemeEntity = themeEntity;
    }
    public interface onItemClickListener {
        void onItemclick(View view, int position);
    }

    private onItemClickListener onItemClickListener;
    public void setOnItemClickListener(ThemeAdapter.onItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public int getID(int position){

        return mThemeEntity.get(position).getId();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder;
        if (viewType == IS_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_head, parent,false);
            viewHolder = new MyViewHolder(view, IS_HEAD);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent,false);
            viewHolder = new MyViewHolder(view, IS_BODY);
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position == 0) {
//            ThemeEntity.EditorsBean mTheme = mThemeEntity.get(position);
            Glide.with(context).load(mmThemeEntity.getEditors().get(position).getAvatar()).into(holder.BJ_TV);

            Glide.with(context).load(mmThemeEntity.getImage()).into(holder.theme_img);

        } else {
            ThemeEntity.StoriesBean mTheme = mThemeEntity.get(position - 1);
            if (mTheme.getImages()==null){
                holder.themeName.setText(mTheme.getTitle());
            }else {
                holder.themeName.setText(mTheme.getTitle());
                Glide.with(context).load(mTheme.getImages().get(0)).into(holder.themeImage);
            }
            if (onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemclick(holder.itemView,holder.getLayoutPosition());
                    }
                });
            }

        }



    }

    @Override
    public int getItemCount() {
        return mThemeEntity.size() + 1;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        /*身体
        * */
        TextView themeName;
        ImageView themeImage;
        //
        ImageView theme_img;
        ImageView BJ_TV;
        public MyViewHolder(View itemView,int type) {
            super(itemView);

            if (type == 0){
                BJ_TV= (ImageView) itemView.findViewById(R.id.BJ_TV);
                theme_img = (ImageView) itemView.findViewById(R.id.theme_img);
            }else{
                themeName = (TextView) itemView.findViewById(R.id.tv_title);
                themeImage = (ImageView) itemView.findViewById(R.id.img);
            }
        }
    }
}
