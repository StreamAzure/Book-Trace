package com.jnu.booktrace.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Drift;
import com.jnu.booktrace.database.DBManager;
import com.jnu.booktrace.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class DriftFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<Drift> drifts;
    
    public DriftFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drift, container, false);
        drifts = new ArrayList<>();
        initFrag(view);
        initPager(); //初始化界面
        //loadDriftData();
        return view;
    }

    private void initFrag(View view) {
        tabLayout = view.findViewById(R.id.drift_tab);
        viewPager2 = view.findViewById(R.id.drift_viewpager2);
    }

    private void initPager() {
        List<Fragment> fragmentList = new ArrayList<>();

    }

    /**
    private void loadDriftData() {//从数据库中加载漂流瓶数据
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                drifts = DatabaseManager.GetDrift();
            }
        });
        thread.start();
        while (true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }**/



}