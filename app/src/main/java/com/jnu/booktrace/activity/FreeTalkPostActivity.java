package com.jnu.booktrace.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jnu.booktrace.R;
import com.jnu.booktrace.adapter.PostAdapter;
import com.jnu.booktrace.adapter.ReplyAdapter;
import com.jnu.booktrace.bean.Reply;
import com.jnu.booktrace.utils.AndroidBarUtils;

import java.util.ArrayList;
import java.util.List;

public class FreeTalkPostActivity extends AppCompatActivity {
    private List<Reply> replyList;
    private int mCurrentPosition = 0;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ReplyAdapter adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_talk_post);

        //状态栏颜色更改
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        AndroidBarUtils.setBarDarkMode(this,true); //状态栏文字图标颜色为黑色

        initToolbar();
        initData();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置RecyclerView内容
        adapter = new ReplyAdapter(this, replyList);
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar(){
        //隐藏默认actionbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //获取toolbar
        toolbar = findViewById(R.id.toolbar);
//        //主标题，必须在setSupportActionBar之前设置，否则无效，如果放在其他位置，则直接setTitle即可
        toolbar.setTitle("详情");
        //用toolbar替换actionbar
        setSupportActionBar(toolbar);
        //左侧按钮：可见+更换图标+点击监听
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示toolbar的返回按钮
        //toolBar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData(){
        replyList = new ArrayList<>();
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
        replyList.add(new Reply(R.drawable.touxiang,"电饭煲","测试回复","2021-10-01"));
    }

}