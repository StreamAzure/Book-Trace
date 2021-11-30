package com.jnu.booktrace.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jnu.booktrace.R;

public class FreeTalkFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_free_talk, container, false);
    }
}