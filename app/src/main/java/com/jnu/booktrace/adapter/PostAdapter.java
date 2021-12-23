package com.jnu.booktrace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Book;
import com.jnu.booktrace.bean.Post;

import org.w3c.dom.Text;

import java.util.List;

//自由谈，话题内主题帖展示页
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Post> adpList;
    private Context context;

    public PostAdapter(Context context, List<Post> postList){
        this.context = context;
        this.adpList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_post,parent,false);
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostHolder postHolder = (PostHolder) holder;
        Post post = adpList.get(position);
        postHolder.ivAvatar.setBackgroundResource(R.drawable.ic_launcher_background);
        postHolder.tvUsername.setText(post.getUsername());
        postHolder.tvContent.setText(post.getContent());
        postHolder.tvDate.setText(post.getDate());
        postHolder.btnRelativeTopic.setText(post.getRelativeTopic());
        postHolder.btnReply.setText(post.getReplyCount()+"");
        postHolder.btnLike.setText(post.getLikeCount()+"");
    }

    @Override
    public int getItemCount() {
        return adpList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
        ImageView ivAvatar;
        TextView tvUsername, tvContent, tvDate;
        Button btnReply, btnLike, btnRelativeTopic;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_item_post_avatar);
            tvUsername = itemView.findViewById(R.id.tv_item_post_username);
            tvContent = itemView.findViewById(R.id.tv_item_post_content);
            tvDate = itemView.findViewById(R.id.tv_item_post_date);
            btnReply = itemView.findViewById(R.id.btn_item_post_reply);
            btnLike = itemView.findViewById(R.id.btn_item_post_like);
            btnRelativeTopic = itemView.findViewById(R.id.btn_item_post_relative_topic);
        }
    }
}
