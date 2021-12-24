package com.jnu.booktrace.Login;

import static android.content.ContentValues.TAG;
import static android.os.Build.HOST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jnu.booktrace.MainActivity;
import com.jnu.booktrace.R;
import com.jnu.booktrace.database.DBManager;
import com.jnu.booktrace.database.DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 作用：此activity用于实现BookTrace的登录功能
 * 功能：登录界面判断，添加用户页面跳转
 * */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static int USER_EXiST=1;
    private EditText nameEt, passwordEt;
    private Button confirmBt, cancelBt;
    private Boolean exist = false,finish = false;
    private LinearLayout processBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComp();   //实现控件的初始化
        String account = String.valueOf(nameEt.getText());
        confirmBt.setOnClickListener(this);
        cancelBt.setOnClickListener(this);
    }

    /**初始化控件*/
    private void initComp() {
        nameEt = findViewById(R.id.login_name);
        passwordEt = findViewById(R.id.login_pwd);
        confirmBt = findViewById(R.id.login_confirm);
        cancelBt = findViewById(R.id.login_cancel);
        processBar = findViewById(R.id.login_processBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_confirm:
                String name = nameEt.getText().toString();
                String password = passwordEt.getText().toString();
                if(name.equals("")){//登录成功
                    Toast.makeText(LoginActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){ //密码为空
                    Toast.makeText(LoginActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    processBar.setVisibility(View.VISIBLE);
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Boolean judge = DatabaseManager.judgeLogin(name,password);
                            Message message = new Message();
                            if(judge){
                                message.what = USER_EXiST;
                                Log.i(TAG, name+"用户存在");
                                exist = true;
                            }
                            finish = true;
                        }
                    });
                    thread.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (true){
                        try {
                            thread.join();
                            break;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if(finish){
                        if(exist){
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.putExtra("name",name);
                            startActivity(intent);
                            finish();
                        }else{
                            processBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this,"密码错误或用户不存在！",Toast.LENGTH_SHORT).show();
                            nameEt.setText("");
                            passwordEt.setText("");
                        }
                    }

                }
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

