package com.jnu.booktrace.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jnu.booktrace.R;
import com.jnu.booktrace.activity.FreeTalkTopicActivity;
import com.jnu.booktrace.adapter.PostAdapter;
import com.jnu.booktrace.adapter.TopicAdapter;
import com.jnu.booktrace.bean.Topic;

import java.util.ArrayList;

public class FreeTalkFragment extends Fragment {
    private ArrayList<Topic> topics;
    public FreeTalkFragment() {
    }

    public static FreeTalkFragment newInstance(String param1, String param2) {
        FreeTalkFragment fragment = new FreeTalkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_free_talk, container, false);
        initData();
        initRecyclerView(rootView);

        return rootView;
    }

    private void initData(){
        topics = new ArrayList<>();
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
        topics.add(new Topic("你读过的第一本哲学书是什么书？","345"));
    }

    private void initRecyclerView(View rootView){
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //设置RecyclerView内容
        TopicAdapter adapter = new TopicAdapter(getContext(), topics);
        recyclerView.setAdapter(adapter);
    }
}