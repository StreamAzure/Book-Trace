package com.jnu.booktrace.person;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jnu.booktrace.R;

/*
 * 个人中心
 * 功能：修改密码、修改个人介绍、修改昵称
 **/
public class PersonInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ActionBar actionBar = getSupportActionBar();     //隐藏自带的标题栏
        if(actionBar!=null){
            actionBar.hide();
        }


    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.person_info_back:
                finish();
                break;
            case R.id.person_info_rl_avatar:
                finish();
                break;
        }
    }
}