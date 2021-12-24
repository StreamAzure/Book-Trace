package com.jnu.booktrace.person;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jnu.booktrace.MainActivity;
import com.jnu.booktrace.R;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText old_password, new_password, new_password2;
    private Button confirm,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initFrag();
    }

    private void initFrag() {
        old_password = findViewById(R.id.change_password_old);
        new_password = findViewById(R.id.change_password_new);
        new_password2 = findViewById(R.id.change_password_new2);
        confirm = findViewById(R.id.change_password_confirm);
        cancel = findViewById(R.id.change_password_cancel);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_password_confirm:
                ChangePassword();
                break;
            case R.id.change_password_cancel:
                finish();
                break;
        }
    }

    private void ChangePassword() {
        String old, new1, new2;
        old = old_password.getText().toString();
        new1 = new_password.getText().toString();
        new2 = new_password2.getText().toString();
        if(!old.equals("")&&!new1.equals("")&&!new2.equals("")){ //数据必须全部填入
            if(!old.equals(MainActivity.person.getPassword())){ //老密码必须相同
                Toast.makeText(ChangePasswordActivity.this,"旧密码输入错误！",Toast.LENGTH_SHORT).show();
                old_password.setText("");
                new_password.setText("");
                new_password2.setText("");
            }else{
                if(!new1.equals(new2)){//两次输入的新密码不一致
                    Toast.makeText(ChangePasswordActivity.this,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();
                    new_password.setText("");
                    new_password2.setText("");
                }else{
                    MainActivity.person.setPassword(new1);
                    Toast.makeText(ChangePasswordActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }else{
            Toast.makeText(ChangePasswordActivity.this,"请输入完整的信息！",Toast.LENGTH_SHORT).show();
        }

    }
}