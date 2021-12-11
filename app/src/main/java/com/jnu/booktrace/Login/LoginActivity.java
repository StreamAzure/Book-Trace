package com.jnu.booktrace.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jnu.booktrace.R;

/*
* 作用：此activity用于实现BookTrace的登录功能
* 功能：登录界面判断，添加用户页面跳转
* */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText accountEt, passwordEt;
    private Button confirmBt, cancelBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComp();   //实现控件的初始化
        String account = String.valueOf(accountEt.getText());
    }

    /**初始化控件*/
    private void initComp() {
        accountEt = findViewById(R.id.login_account);
        passwordEt = findViewById(R.id.login_pwd);
        confirmBt = findViewById(R.id.login_confirm);
        cancelBt = findViewById(R.id.login_cancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_confirm:
                break;
            case R.id.login_cancel:
                break;
            case R.id.login_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}