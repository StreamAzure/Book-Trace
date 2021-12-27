package com.jnu.booktrace.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jnu.booktrace.R;
import com.jnu.booktrace.adapter.PostAdapter;
import com.jnu.booktrace.bean.Post;
import com.jnu.booktrace.bean.Topic;
import com.jnu.booktrace.utils.AndroidBarUtils;

import java.util.ArrayList;

//单一话题页，展示该话题下的相关主题帖
public class FreeTalkTopicActivity extends AppCompatActivity {
    private ArrayList<Post> posts;
    private int[] colors;
    //因为setExpanded会调用事件监听，所以通过标志过滤掉
    public static int expendedtag=2;
    private TabLayout tabLayout;
    private Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_talk_topic);

        initColors();
        initToolbar();
        initData();
        initTabLayout();

        //状态栏颜色更改
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //状态栏文字图标颜色为黑色
        AndroidBarUtils.setBarDarkMode(this,true);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置RecyclerView内容
        PostAdapter adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);
    }

    private void initColors(){
        this.colors = new int[]{getColor(R.color.topic_blue),
                getColor(R.color.topic_brown),
                getColor(R.color.topic_green),
                getColor(R.color.topic_grey)
        };
    }

    private void initTabLayout(){
        String[] titles = new String[]{"最新"};
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab()); //只有一个标签
        for(int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }

    private void initToolbar(){
        Intent intent = getIntent();
        topic = (Topic)intent.getParcelableExtra("topic");
        int position = intent.getIntExtra("position",0);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        //设置折叠布局内部的toolbar在折叠时可充当ActionBar标题栏
        Toolbar toolbar = findViewById(R.id.toolbar);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if(null != actionBar){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getWindow().setStatusBarColor(colors[position%colors.length]);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            //verticalOffset是当前appbarLayout的高度与最开始appbarlayout高度的差，向上滑动的话是负数
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                Log.e("count"," "+verticalOffset);
//                Log.e("count","getsupportActionbar "+getSupportActionBar().getHeight()
//                +" collapsing "+collapsingToolbarLayout.getHeight());
                if(getSupportActionBar().getHeight() - collapsingToolbarLayout.getHeight() == verticalOffset){
                    //折叠监听
                    //Toast.makeText(FreeTalkTopicActivity.this,"折叠了",Toast.LENGTH_SHORT).show();
                    toolbar.setTitle(topic.getTitle());
                }
                if(expendedtag==2&&verticalOffset==0){
                    //展开监听
                    //Toast.makeText(FreeTalkTopicActivity.this, "展开了",Toast.LENGTH_SHORT).show();
                    toolbar.setTitle("");
                }
//                if(expendedtag!=2&&verticalOffset==0){
//                    expendedtag++;
//                }
            }
        });
        TextView tvTopicTitle = findViewById(R.id.tv_topic_title);
        tvTopicTitle.setText(topic.getTitle());
        //设置折叠标题栏的内容
        TextView tvTopicContent = findViewById(R.id.tv_topic_content);
        tvTopicContent.setText(topic.getContent());
        //设置折叠标题栏的背景颜色
        collapsingToolbarLayout.setBackgroundColor(colors[position%colors.length]);
        //设置顶部标题栏的背景色
        toolbar.setBackgroundColor(colors[position%colors.length]);
    }

    private void initData(){
        posts = new ArrayList<>();
        posts.add(new Post(R.drawable.pic1, "缄言","这是书店日记吗",topic.getTitle()));
        posts.add(new Post(R.drawable.pic2,"碎","哈哈哈哈书店四季？",topic.getTitle()));
        posts.add(new Post(R.drawable.pic3,"Shirley\uD83C\uDF6D","貌似手裡有書，心不慌！",topic.getTitle()));
        posts.add(new Post(R.drawable.pic4,"狗腿子","为猫仔来一通乱赞\uD83D\uDC4F",topic.getTitle()));
        posts.add(new Post(R.drawable.pic5,"冬妮娅","旁边的面包看着好好吃",topic.getTitle()));
        posts.add(new Post(R.drawable.pic6,"记忆中的夏天","我手中的书估计你不会想要",topic.getTitle()));
        posts.add(new Post(R.drawable.pic7,"悬铃木下","书店四季",topic.getTitle()));
        posts.add(new Post(R.drawable.pic8,"米小姐","哇，肉桂卷",topic.getTitle()));
    }
}