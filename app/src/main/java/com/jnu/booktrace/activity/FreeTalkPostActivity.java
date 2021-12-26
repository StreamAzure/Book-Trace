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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_talk_post);

        //状态栏颜色更改
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        AndroidBarUtils.setBarDarkMode(this,true); //状态栏文字图标颜色为黑色

        initData();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置RecyclerView内容
        adapter = new ReplyAdapter(this, replyList);
        recyclerView.setAdapter(adapter);
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