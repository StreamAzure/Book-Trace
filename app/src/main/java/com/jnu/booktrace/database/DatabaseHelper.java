package com.jnu.booktrace.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static Integer Version = 1;     //数据库版本号

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //context:上下文 name：数据库名称 factory:可选的游标工厂（通常是NULL） version：数据库版本，值必须为整数且递增
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户信息表
        String sql = "create table persontb (id integer primary key autoincrement, name varchar(25) not null unique, " +
                "password varchar(50) not null, nickname varchar(20), description varchar(100))";
        db.execSQL(sql);

        //创建书籍基本信息表，ISBN号均可作为主键
        String createBookTable = "create table booktb (isbn varchar(15) not null unique, " +
                "id varchar(25), title text, image text, author varchar(100), translator varchar(100), " +
                "publisher varchar(100), pubdate varchar(25), tags varchar(100), binding varchar(20), price varchar(25), "+
                "pages integer, author_intro text, summary text)";
        db.execSQL(createBookTable);

    }

    //数据库版本发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
