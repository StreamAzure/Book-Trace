package com.jnu.booktrace.person;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jnu.booktrace.MainActivity;
import com.jnu.booktrace.R;
import com.jnu.booktrace.database.DatabaseManager;
import com.mysql.jdbc.MiniAdmin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * 个人中心
 * 功能：修改密码、修改个人介绍、修改昵称
 **/
public class PersonInfoActivity extends AppCompatActivity {
    private TextView gender, birth, nickname,description;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        intent = getIntent();

        ActionBar actionBar = getSupportActionBar();     //隐藏自带的标题栏
        if(actionBar!=null){
            actionBar.hide();
        }
        initFrag();
        setListener();
    }

    //重写返回事件，返回更新数据库
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updatePerson();
    }

    private void initFrag() {
        nickname = findViewById(R.id.person_info_tv_nickname2);
        nickname.setText(MainActivity.person.getNickName());
        gender = findViewById(R.id.person_info_tv_gender2);
        gender.setText(MainActivity.person.getGender());
        birth = findViewById(R.id.person_info_tv_birth2);
        birth.setText(MainActivity.person.getBirth());
        description = findViewById(R.id.person_info_tv_description2);
        description.setText(MainActivity.person.getDescription());
    }
    private void setListener() {
        nickname.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!nickname.getText().toString().equals(MainActivity.person.getNickName())){
                    MainActivity.person.setNickName(nickname.getText().toString());
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.person_info_back:
                updatePerson();
                finish();
                break;
            case R.id.person_info_rl_avatar:

                break;
            case R.id.person_info_rl_gender:
                showGenderDialog();
                break;
            case R.id.person_info_rl_birth:
                showBirthDialog();
                break;
            case R.id.person_info_rl_description:
                showDescriptionDialog();
                break;
            case R.id.person_info_rl_password:
                intent = new Intent(this,ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    //更新个人数据
    private void updatePerson(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseManager.updatePersontb(MainActivity.person);
            }
        });
        thread.start();
        while (true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //选择性别对话框
    private void showGenderDialog(){
        List<String> items = new ArrayList<>();
        items.add("未知");
        items.add("男");
        items.add("女");
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(PersonInfoActivity.this, new OnOptionsSelectListener(){
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                gender.setText(items.get(options1));
                MainActivity.person.setGender(items.get(options1));
            }
        }).setSelectOptions(0)
                .setOutSideCancelable(false)
                .build();
        optionsPickerView.setPicker(items);
        optionsPickerView.show();
    }

    //选择生日对话框
    private void showBirthDialog(){
        TimePickerView pvTime = new TimePickerBuilder(PersonInfoActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                birth.setText(format.format(date));
                MainActivity.person.setBirth(format.format(date));
            }
        }).build();
        pvTime.show();
    }

    public void showDescriptionDialog(){
        DescriptionDialog descriptionDialog = new DescriptionDialog(this);
        descriptionDialog.show();
        descriptionDialog.setEditText(MainActivity.person.getDescription());
        descriptionDialog.setDialogSize();
        descriptionDialog.setOnEnsureListener(new DescriptionDialog.OnEnsureListener() {
            @Override
            public void onEnsure() {
                String text = descriptionDialog.getEditText();  //获取输入的数据
                if(!TextUtils.isEmpty(text)){
                    description.setText(text);
                    MainActivity.person.setDescription(text);
                }
                descriptionDialog.cancel();
            }
        });
    }
}