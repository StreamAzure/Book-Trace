package com.jnu.booktrace;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.jnu.booktrace.fragments.DriftFragment;
import com.jnu.booktrace.fragments.FreeTalkFragment;
import com.jnu.booktrace.fragments.LibraryFragment;
import com.jnu.booktrace.fragments.PersonFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMS_REQUEST_CODE = 100;

    BottomNavigationView bottomNavigationView;
    private List<Fragment> fragmentList;
    private LibraryFragment libraryFragment;
    private DriftFragment driftFragment;
    private FreeTalkFragment freeTalkFragment;
    private PersonFragment personFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment(); //将底部导航栏各按钮与对应Fragment绑定
        myRequestPermission();
    }

    private void myRequestPermission(){
        //6.0版本或以上需请求权限
        String[] permissions=new String[]{Manifest.permission.
                WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(permissions,PERMS_REQUEST_CODE);
        }
    }

    private void initFragment() {
        bottomNavigationView = findViewById(R.id.bnv_menu);

        libraryFragment = new LibraryFragment();
        setFragment(libraryFragment);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.item_bottom_library){
                    if(libraryFragment == null){
                        libraryFragment = new LibraryFragment();
                    }
                    setFragment(libraryFragment);
                }
                else if(item.getItemId()==R.id.item_bottom_drift){
                    if(driftFragment == null){
                        driftFragment = new DriftFragment();
                    }
                    setFragment(driftFragment);
                }
                else if(item.getItemId()==R.id.item_bottom_freetalk){
                    if(freeTalkFragment == null){
                        freeTalkFragment = new FreeTalkFragment();
                    }
                    setFragment(freeTalkFragment);
                }
                else if(item.getItemId()==R.id.item_bottom_person){
                    if(personFragment == null){
                        personFragment = new PersonFragment();
                    }
                    setFragment(personFragment);
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

}