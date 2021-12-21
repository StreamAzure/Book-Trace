package com.jnu.booktrace.database;


import android.annotation.SuppressLint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jnu.booktrace.bean.Book;
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

    public static void insertBooktb(Book book){
        ContentValues contentValues = new ContentValues();
        contentValues.put("isbn",book.getIsbn10());
        contentValues.put("title",book.getTitle());
        contentValues.put("image",book.getImage());
        contentValues.put("author",book.getAuthor());
        contentValues.put("translator",book.getTranslator());
        contentValues.put("publisher",book.getPublisher());
        contentValues.put("pubdate",book.getPubdate());
        contentValues.put("tags",book.getTags());
        contentValues.put("binding",book.getBinding());
        contentValues.put("price",book.getPrice());
        contentValues.put("pages",book.getPages());
        contentValues.put("author_intro",book.getAuthor_intro());
        contentValues.put("summary",book.getSummary());
        db.insert("booktb",null,contentValues);
    }

    //判断isbn号对应的书籍是否在数据库中
    public static Boolean isBookExist(String isbn){
        String sql = "select * from booktb where isbn = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{isbn});
        cursor.moveToFirst();
        return !cursor.isAfterLast();
    }
}
