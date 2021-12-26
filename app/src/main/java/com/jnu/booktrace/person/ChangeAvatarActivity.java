package com.jnu.booktrace.person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.jnu.booktrace.R;
import com.jnu.booktrace.fragments.ShowBottomDialog;

import com.jnu.booktrace.imagehandle.GlideEngine;
import com.jnu.booktrace.imagehandle.ImageHandle;

import java.io.File;
import java.util.ArrayList;


public class ChangeAvatarActivity extends AppCompatActivity {
    private ShowBottomDialog showBottomDialog;
    private ImageView imageView_tx;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        initFrag();
        initPic();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Bitmap bitmap2= ImageHandle.makeBitmapSquare(imageBitmap,120);
            RoundedBitmapDrawable roundImg1= RoundedBitmapDrawableFactory.create(getResources(),bitmap2);
            roundImg1.setAntiAlias(true);
            roundImg1.setCornerRadius(bitmap2.getWidth()/2);
            imageView_tx.setImageDrawable(roundImg1);
        }
    }

    private void initPic() {
        Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.test_bg);
        Bitmap bitmap2= ImageHandle.makeBitmapSquare(bitmap1,120);
        RoundedBitmapDrawable roundImg1= RoundedBitmapDrawableFactory.create(getResources(),bitmap2);
        roundImg1.setAntiAlias(true);
        roundImg1.setCornerRadius(bitmap2.getWidth()/2);
        imageView_tx.setImageDrawable(roundImg1);
    }

    private void initFrag() {
        BottomDialog(this);
        imageView_tx =(ImageView)findViewById(R.id.drift_ig);
    }



    public void onClick(View view) {
        switch (view.getId()){
            case R.id.drift_change:
                BottomDialog(this);
        }
    }
    public void BottomDialog(Context context) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(context, R.layout.bottom_dialog, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.bottom_tv_look).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.bottom_tv_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyPhotos.createAlbum(ChangeAvatarActivity.this, true,false, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，是否使用宽高数据（false时宽高数据为0，扫描速度更快），[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                        .setFileProviderAuthority("com.jnu.booktrace")//参数说明：见下方`FileProvider的配置`
                        .start(new SelectCallback() {
                            @Override
                            public void onResult(ArrayList<Photo> photos, boolean isOriginal) {
                                //获取file,进行对应操作
                                File file = new File(photos.get(0).path);

                            }

                            @Override
                            public void onCancel() {
                            }
                        });
            }
        });

        dialog.findViewById(R.id.bottom_tv_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyPhotos.createCamera((Activity) context, true)//参数说明：上下文
                        .setFileProviderAuthority("com.jnu.booktrace")
                        .start(new SelectCallback() {
                            @Override
                            public void onResult(ArrayList<Photo> photos, boolean isOriginal) {
                                //获取file,进行对应操作
                                File file = new File(photos.get(0).path);
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });

        dialog.findViewById(R.id.bottom_tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


}