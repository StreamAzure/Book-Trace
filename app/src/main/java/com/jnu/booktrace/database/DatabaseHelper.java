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
        String sqlCreatePersonTable = "create table persontb (id integer primary key autoincrement, name varchar(25) not null unique, " +
                "password varchar(50) not null, nickname varchar(20), description varchar(100))";
        //自由谈：话题（后台发布），ID，话题名称，话题描述，参与用户（字符串格式维护一个用户ID列表），相关主题数量
        String sqlCreateTopicTable = "create table topictb(id integer primary key autoincrement, title varchar(200) not null, "+
                "description text, subscribers text, postCount integer)";
        //自由谈：主题帖（用户在某一话题下发布）,ID，内容，日期（yyyy-MM-dd格式字符串），作者用户ID（一个整数），所属话题ID，赞同数，回复数
        String sqlCreatePostTable = "create table posttb(id integer primary key autoincrement, "+
                "content text not null, date text, author integer, relativeTopic integer, likeCount integer, replyCount integer)";
        //自由谈：回复（在主题贴下由用户发布），ID，内容，日期（yyyy-MM-dd格式字符串），作者用户ID，所属主题帖ID，赞同数
        String sqlCreateReplyTable = "create table replytb(id integer primary key autoincrement, content text not null, date text, "+
                "author integer, relativePost integer, likeCount integer)";

        db.execSQL(sqlCreatePersonTable);
        db.execSQL(sqlCreateTopicTable);
        db.execSQL(sqlCreatePostTable);
        db.execSQL(sqlCreateReplyTable);
    }

    //数据库版本发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
