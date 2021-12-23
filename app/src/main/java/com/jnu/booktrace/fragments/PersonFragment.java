package com.jnu.booktrace.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnu.booktrace.R;
import com.jnu.booktrace.bean.Person;
import com.jnu.booktrace.database.DBManager;
import com.jnu.booktrace.database.DatabaseManager;
import com.jnu.booktrace.person.PersonInfoActivity;


public class PersonFragment extends Fragment implements View.OnClickListener {
    private Person person;
    private String name;
    private TextView person_tv_name, person_tv_description;
    private ImageView person_iv_avatar;
    private ImageButton person_bt_person, person_bt_drift,person_bt_topic,person_bt_collect;


    public PersonFragment(String name) {
        this.name = name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        initFrag(view); //绑定组件
        setTopName();  //设置头顶姓名和个人描述
        setButtonClick(); //设置imageButton监听
        return view;
    }

    private void setButtonClick() {
        person_bt_person.setOnClickListener(this);
        person_bt_drift.setOnClickListener(this);
        person_bt_topic.setOnClickListener(this);
        person_bt_collect.setOnClickListener(this);
    }

    private void setTopName() {
        person_tv_name.setText(name);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                person = DatabaseManager.getPersonFromName(name);
            }
        });
        thread.start();
        while(true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String description = person.getDescription();
        person_tv_description.setText(description);
        Bitmap bitmap = BitmapFactory.decodeFile(person.getAvatar());
        person_iv_avatar.setImageBitmap(bitmap);
    }

    private void initFrag(View view) {
        person_tv_name = view.findViewById(R.id.person_tv_name);
        person_tv_description =view.findViewById(R.id.person_tv_description);
        person_bt_person = view.findViewById(R.id.person_bt_person);
        person_bt_drift = view.findViewById(R.id.person_bt_drift);
        person_bt_topic = view.findViewById(R.id.person_bt_topic);
        person_bt_collect = view.findViewById(R.id.person_bt_collect);
        person_iv_avatar = view.findViewById(R.id.person_iv_avatar);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.person_bt_person:
                intent = new Intent(PersonFragment.this.getContext(), PersonInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.person_bt_drift:
                break;
            case R.id.person_bt_topic:
                break;
            case R.id.person_bt_collect:
                break;
        }
    }
}