package com.jnu.booktrace.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jnu.booktrace.R;
import com.jnu.booktrace.adapter.PostAdapter;
import com.jnu.booktrace.bean.Post;

import java.util.ArrayList;

//单一话题页，展示该话题下的相关主题帖
public class FreeTalkTopicActivity extends AppCompatActivity {
    private ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_talk_topic);

        initData();

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        //设置折叠布局内部的toolbar在折叠时可充当ActionBar标题栏
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置折叠标题栏的标题
        collapsingToolbarLayout.setTitle("自由谈");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置RecyclerView内容
        PostAdapter adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        posts = new ArrayList<>();
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
        posts.add(new Post("电饭煲","1234567777777","你好，测试"));
    }
}