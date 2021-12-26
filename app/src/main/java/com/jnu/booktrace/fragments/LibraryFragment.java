package com.jnu.booktrace.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jnu.booktrace.R;
import com.jnu.booktrace.activity.LibraryBookOverviewActivity;
import com.jnu.booktrace.bean.Book;
import com.jnu.booktrace.utils.ISBNApiUtil;
import com.kongzue.dialogx.DialogX;
import com.kongzue.dialogx.dialogs.InputDialog;
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener;

public class LibraryFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
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
        DialogX.init(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        initToolbar(rootView);
        initSpinner(rootView);

        floatingActionButton = rootView.findViewById(R.id.fab_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialog inputDialog = new InputDialog("添加书籍", "请输入书籍的ISBN号", "添加", "取消", "")
                        .setCancelable(false)
                        .setOkButton(new OnInputDialogButtonClickListener<InputDialog>() {
                            @Override
                            public boolean onClick(InputDialog baseDialog, View v, String inputStr) {
                                if(!inputStr.isEmpty()){
                                    Book book = new Book();
                                    Toast.makeText(getContext(),"查询书籍中……",Toast.LENGTH_LONG).show();
                                    new ISBNApiUtil().getBookFromISBN(book,inputStr);
                                    if(book.getTitle() == null){
                                        Toast.makeText(getContext(),"书籍添加失败！请确认ISBN号是否正确",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getContext(),"书籍添加成功",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(getContext(),"请输入ISBN号",Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        });
                inputDialog.show();
            }
        });

        Button btnBookShow = rootView.findViewById(R.id.toBookShowActivity);
        btnBookShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LibraryBookOverviewActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void initToolbar(View v){
        //获取toolbar
        toolbar = v.findViewById(R.id.toolbar);
//        //主标题，必须在setSupportActionBar之前设置，否则无效，如果放在其他位置，则直接setTitle即可
        toolbar.setTitle("");
        //用toolbar替换actionbar
        //setSupportActionBar(toolbar);
    }

    private void initSpinner(View v){
        spinner = v.findViewById(R.id.spinner);
        // 建立数据源
        String[] mItems = {"Item 1", "Item 2", "Item 3", "Item 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, mItems);
        //spinner_item 直接显示的样式
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        //spinner_dropdown_item 下拉的样式
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO
            }
        });
    }

}