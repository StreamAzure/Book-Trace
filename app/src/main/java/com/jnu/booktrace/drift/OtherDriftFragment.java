package com.jnu.booktrace.drift;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jnu.booktrace.R;


public class OtherDriftFragment extends Fragment {

    public OtherDriftFragment() {
        // Required empty public constructor
    }

    public static OtherDriftFragment newInstance(String param1, String param2) {
        OtherDriftFragment fragment = new OtherDriftFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_drift, container, false);

        return view;
    }
}