package com.example.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhihu.R;
import com.example.zhihu.bean.LeftEntity;

import java.util.List;

/**
 * Created by 44744 on 2016/4/19.
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    List<LeftEntity.OthersBean> themesEntities;
    Context context;
    /*  当前是头部**/
    public final int IS_HEAD = 0;
    /* 当前是body **/
    public final int IS_BODY = 1;


    public LeftAdapter(List<LeftEntity.OthersBean> themesEntities, Context mContext) {
        this.themesEntities = themesEntities;
        this.context = mContext;
    }

    public onItemClickListener onItemClickListener;

    public void setOnItemClickListener(LeftAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /* 更新数据 **/
    public void setThemesEntities(List<LeftEntity.OthersBean> themesEntities) {
        this.themesEntities = themesEntities;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder;
        if (viewType == IS_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_header_main, parent, false);
            viewHolder = new MyViewHolder(view, IS_HEAD);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_left_recycle, parent, false);
            viewHolder = new MyViewHolder(view, IS_BODY);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (position == 0) {
            if (onItemClickListener != null) {

                holder.leftHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int layoutPosition = holder.getLayoutPosition();
                        onItemClickListener.onItemclick(v, layoutPosition);
                    }
                });
            }

        } else {
            LeftEntity.OthersBean othersEntity = themesEntities.get(position - 1);
            holder.leftName.setText(othersEntity.getName());
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int layoutPosition = holder.getLayoutPosition();
                        onItemClickListener.onItemclick(v, layoutPosition);
                    }
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return IS_HEAD;
        else
            return IS_BODY;
    }

    @Override
    public int getItemCount() {
        return themesEntities.size() + 1;
    }

    public interface onItemClickListener {
        void onItemclick(View view, int position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

         /*头部
         * **/
         LinearLayout leftHome;


        /*身体
        * */
        TextView leftName;
        ImageView leftImage;

        public MyViewHolder(View itemView, int type) {
            super(itemView);


            if (type == 0) {
                leftHome = (LinearLayout) itemView.findViewById(R.id.left_home);

            } else {
                leftName = (TextView) itemView.findViewById(R.id.left_name);
                leftImage = (ImageView) itemView.findViewById(R.id.left_image);
            }
        }

    }
}
