package com.example.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhihu.R;
import com.example.zhihu.bean.CommentEntity;
import com.example.zhihu.comment.view.ICommentView;
import com.example.zhihu.utils.TimeTransition;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 44744 on 2016/4/29.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context mContext;
    private CommentEntity commentEntity;
    private String long_comments, short_comments;
    private ICommentView mICommentView;

    public List<CommentEntity.CommentsBean> mBean = new ArrayList<>();
    int longCommentSize;


    public CommentAdapter(Context mContext, CommentEntity commentEntity, String long_comments, String short_comments, ICommentView iCommentView) {
        this.commentEntity = commentEntity;
        this.mContext = mContext;
        this.long_comments = long_comments;
        this.short_comments = short_comments;
        this.mICommentView = iCommentView;
        longCommentSize = commentEntity.getComments().size();
        mBean.addAll(commentEntity.getComments());

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.comment_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view1);//用户评论的类型
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(position == 0){
            //进入方法体后，再次判断
            if(position == longCommentSize -1){
                holder.comment_count.setVisibility(View.VISIBLE);//显示
                holder.comment_count.setText(long_comments + " 条长评");
                holder.comment_count_buttom.setVisibility(View.VISIBLE);
                holder.comment_count_buttom.setText(short_comments+" 条短评");
                //
                holder.comment_layout_username.setText(mBean.get(position).getAuthor());//设置用户名
                holder.comment_layout_thumb_number.setText(mBean.get(position).getLikes() + "");
                holder.comment_layout_comment_time.setText(TimeTransition.timestampToString(mBean.get(position).getTime()) + "");
                holder.comment_layout_user_comment.setText(mBean.get(position).getContent());//设置用户的评论
                Glide.with(mContext)
                        .load(mBean.get(position).getAvatar())
                        .into(holder.comment_layout_user_icon);//设置评论用户的头像
            }else {
                holder.comment_count.setVisibility(View.VISIBLE);//显示
                holder.comment_count.setText(long_comments + " 条长评");
                holder.comment_count_buttom.setVisibility(View.GONE);//把底部的显示隐藏


                holder.comment_layout_username.setText(mBean.get(position).getAuthor());//设置用户名
                holder.comment_layout_thumb_number.setText(mBean.get(position).getLikes() + "");
                holder.comment_layout_comment_time.setText(TimeTransition.timestampToString(mBean.get(position).getTime()) + "");
                holder.comment_layout_user_comment.setText(mBean.get(position).getContent());//设置用户的评论
                Glide.with(mContext)
                        .load(mBean.get(position).getAvatar())
                        .into(holder.comment_layout_user_icon);//设置评论用户的头像

                //进入方法后，再次判断
            }

        }else if(position == longCommentSize-1){
            holder.comment_count.setVisibility(View.GONE);//显示
            holder.comment_count_buttom.setVisibility(View.VISIBLE);//把底部的显示隐藏
            holder.comment_count_buttom.setText(short_comments+" 条短评");


            holder.comment_layout_username.setText(mBean.get(position).getAuthor());//设置用户名
            holder.comment_layout_thumb_number.setText(mBean.get(position).getLikes() + "");//设置评论的点赞数
            holder.comment_layout_comment_time.setText(TimeTransition.timestampToString(mBean.get(position).getTime()) + "");//设置评论的时间
            holder.comment_layout_user_comment.setText(mBean.get(position).getContent());//设置用户的评论
            Glide.with(mContext)
                    .load(mBean.get(position).getAvatar())
                    .into(holder.comment_layout_user_icon);//设置评论用户的头像

        }else{//否则的话就是内容体
            //把顶部和底部都隐藏
            holder.comment_count.setVisibility(View.GONE);
            holder.comment_count_buttom.setVisibility(View.GONE);
            //
            holder.comment_layout_username.setText(mBean.get(position).getAuthor());//设置用户名
            holder.comment_layout_thumb_number.setText(mBean.get(position).getLikes() + "");
            holder.comment_layout_comment_time.setText(TimeTransition.timestampToString(mBean.get(position).getTime()) + "");
            holder.comment_layout_user_comment.setText(mBean.get(position).getContent());//设置用户的评论
            Glide.with(mContext)
                    .load(mBean.get(position).getAvatar())
                    .into(holder.comment_layout_user_icon);//设置评论用户的头像
        }
        holder.comment_count_buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"点击了 获取短评",Toast.LENGTH_SHORT).show();
                mICommentView.ShowShortCommentClick();
//                holder.comment_count_buttom.setClickable(false);//设置点击后，不可再次点击
                if (holder.comment_count_buttom.isClickable()==true) {
                    holder.comment_count_buttom.setClickable(false);
                }
            }
        });
    }
    //此方法是添加长评的方法
    public void setmBean(CommentEntity mCommentDetailBean){
        this.mBean.addAll(mCommentDetailBean.getComments());//把获取到的短评论添加进集合
        notifyDataSetChanged();//更新数据

    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //显示评论数的控件的初始化部分
        private TextView comment_count;
        //底部显示评论数
        TextView comment_count_buttom;
        //显示用户评论的各控件部分

        private CircleImageView comment_layout_user_icon;
        private TextView comment_layout_username, comment_layout_thumb_number, comment_layout_user_comment, comment_layout_comment_time;

        public ViewHolder(View itemView) {
            super(itemView);
            comment_count = (TextView) itemView.findViewById(R.id.comment_count);//显示长短短评数量的TextView
            comment_count_buttom = (TextView) itemView.findViewById(R.id.comment_count_buttom);//显示短评论的底部显示

            comment_layout_username = (TextView) itemView.findViewById(R.id.comment_layout_username);//显示用户名
            comment_layout_thumb_number = (TextView) itemView.findViewById(R.id.comment_layout_thumb_number);//显示点赞数
            comment_layout_user_comment = (TextView) itemView.findViewById(R.id.comment_layout_user_comment);//显示用户评论
            comment_layout_comment_time = (TextView) itemView.findViewById(R.id.comment_layout_comment_time);//显示评论时间
            comment_layout_user_icon = (CircleImageView) itemView.findViewById(R.id.comment_layout_user_icon);//显示用户头像
        }
    }
}
