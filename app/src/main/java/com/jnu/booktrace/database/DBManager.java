package com.jnu.booktrace.database;


import android.annotation.SuppressLint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jnu.booktrace.bean.Person;

/*
* 负责管理数据库对表中的数据进行增删改查
**/
public class DBManager {
    private static SQLiteDatabase db;

    public static void initDB(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context,"BookTrace.db",null,1);
        db = databaseHelper.getWritableDatabase();
    }
    
    /*
    * 向用户表中插入数据
     */
    public static void insertPersontb(Person person){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",person.getName());
        contentValues.put("password",person.getPassword());
        contentValues.put("nickname",person.getNickName());
        contentValues.put("description",person.getDescription());
        db.insert("persontb",null,contentValues);
    }
    /*
    * 判断输入的用户在数据库中是否存在
     */
    public static Boolean JudgePersonExist(String name){
        String sql = "select count(*) from persontb where name=?";
        Cursor cursor = db.rawQuery(sql,new String[]{name});
        int result=0;
        //判断
        if(cursor.moveToFirst()){
            @SuppressLint("Range") int count = cursor.getInt(cursor.getColumnIndex("count(*)"));
            result = count;
        }
        if(result==1) return true;
        else return false;
        
    }
}
