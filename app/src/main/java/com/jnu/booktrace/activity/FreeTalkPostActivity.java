package com.jnu.booktrace.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jnu.booktrace.R;
import com.jnu.booktrace.adapter.PostAdapter;
import com.jnu.booktrace.adapter.ReplyAdapter;
import com.jnu.booktrace.bean.Reply;

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

        replyList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //设置RecyclerView内容
        adapter = new ReplyAdapter(this, replyList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mCurrentPosition != layoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = layoutManager.findFirstVisibleItemPosition();
                    //第3个开始   这个就是你需要从第几个开始悬浮
                    if(mCurrentPosition<1){
                        //这个是在recyclerview同级别的悬浮
                        layoutManager.get.setVisibility(View.GONE);
                    }else if(mCurrentPosition>=1){
                        mBinding.tabIndex.setVisibility(View.VISIBLE);
                        //这个是将同级别的悬浮  放置在顶部的距离  距离顶部多少dp  sumHeight我已经计算好了
                        mBinding.tabIndex.setTop(56);
                    }
                }
            }
        });
    }

}