package com.jnu.booktrace.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jnu.booktrace.R;
import com.jnu.booktrace.activity.LibraryBookOverviewActivity;
import com.jnu.booktrace.bean.Book;
import com.jnu.booktrace.utils.ISBNApiUtil;

public class LibraryFragment extends Fragment {

    private Button button;
    private EditText editText;
    private FloatingActionButton floatingActionButton;
    private Spinner spinner;

    public LibraryFragment() {}
    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        floatingActionButton = rootView.findViewById(R.id.fab_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:对话框
            }
        });
        button = rootView.findViewById(R.id.btn);
        Button btnBookShow = rootView.findViewById(R.id.toBookShowActivity);
        editText = rootView.findViewById(R.id.et);
        button.setText("提交");
        Book book = new Book();
        btnBookShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LibraryBookOverviewActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ISBNApiUtil().getBookFromISBN(book,editText.getText().toString());
            }
        });
        return rootView;
    }

    private void initSpinner(View v){
        spinner = v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] spinnerContent = new String[]{"默认","玄幻","同人文"};
                Toast.makeText(getContext(), "你点击的是:" + spinnerContent[position],
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




}