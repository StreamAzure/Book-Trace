package com.jnu.booktrace.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Person;
import com.jnu.booktrace.database.DBManager;

/*
* 作用：此activity用于实现BookTrace的注册功能
* 功能：实现注册逻辑，将用户添加到数据库中
**/
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText register_name,register_password,register_password2;
    private Button register_confirm, register_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComp();  //初始化控件
        //设置按钮监听事件
        register_confirm.setOnClickListener(this);
        register_cancel.setOnClickListener(this);
    }

    /**初始化控件*/
    private void initComp() {
        register_name = findViewById(R.id.register_name);
        register_password = findViewById(R.id.register_password);
        register_password2 = findViewById(R.id.register_password2);
        register_confirm = findViewById(R.id.register_confirm);
        register_cancel = findViewById(R.id.register_cancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_confirm:   //注册按钮
                Confirm();
                break;
            case R.id.register_cancel:   //取消按钮,直接返回
                finish();
                break;
        }
    }

    private void Confirm() {
        String name, password, password2;
        Person person = new Person();
        register_name.getText().toString();
        name = register_name.getText().toString();
        password = register_password.getText().toString();
        password2 = register_password2.getText().toString();
        if(name != null && password!= null && password2!= null){
            if(!password.equals(password2)){//判断两次输入的密码是否一致
                Toast.makeText(RegisterActivity.this,"两次输入的密码不一致，请重新输入",
                        Toast.LENGTH_SHORT).show();
            } else{//判断现在添加的用户是否以及存在，不存在则添加
                if(DBManager.JudgePersonExist(name)){
                    Toast.makeText(RegisterActivity.this,"输入的用户已存在，请重新输入",
                            Toast.LENGTH_SHORT).show();
                }else{
                    person.setName(name);
                    person.setPassword(password);

                }
            }
        }else {
            Toast.makeText(RegisterActivity.this,"请输入完整的信息",Toast.LENGTH_SHORT).show();
        }
    }
}