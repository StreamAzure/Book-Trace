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
import com.jnu.booktrace.imagehandle.ImageHandle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
                //String imageStr = ImageHandle.getImageStr("star.png");
                String imageStr = null;
                try {
                    imageStr = readFile("star.png");
                    Log.e("Login", imageStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("Login", imageStr);
//                boolean generateImage = ImageHandle.generateImage(imageStr, "1.jpg");
//                System.out.println(generateImage);
                break;
            case R.id.login_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }


    //读数据
    public String readFile(String fileName) throws IOException {
        String res = "";//有问题，没有更新res的地方，始终为空
        try {
            FileInputStream fin = openFileInput(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    //写数据
    public void writeFile(String fileName,String writestr) throws IOException{
        try{
            FileOutputStream fout =openFileOutput(fileName, MODE_PRIVATE);
            byte [] bytes = writestr.getBytes();
            fout.write(bytes);
            fout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

