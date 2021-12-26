package com.jnu.booktrace.database;


import android.annotation.SuppressLint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jnu.booktrace.bean.Book;
import com.jnu.booktrace.bean.Drift;
import com.jnu.booktrace.bean.Person;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    * 判断本地数据库是否存在用户信息
     */
    public static Boolean JudgePersonIsNotNull(){
        String sql = "select count(*) from persontb";
        Cursor cursor = db.rawQuery(sql,null);
        int result = 0 ;
        if(cursor.moveToFirst()){
            @SuppressLint("Range") int count = cursor.getInt(cursor.getColumnIndex("count(*)"));
            result = count;
        }
        if(result==0) return false;
        else return true;
    }

    /*
    * 从用户表中获取姓名信息
     */
    @SuppressLint("Range")
    public static String GetNameFromPersontb(){
        String sql = "select * from persontb";
        String name ="";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        return name;
    }
    /*
    * 根据用户姓名获取用户
     */
    @SuppressLint("Range")
    public static Person getPersonFromName(String name){
        Person person = new Person();
        String sql = "select * from persontb where name = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{name});
        cursor.moveToFirst();
        person.setId(cursor.getInt(cursor.getColumnIndex("id")));
        person.setName(name);
        person.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        person.setNickName(cursor.getString(cursor.getColumnIndex("nickname")));
        person.setGender(cursor.getString(cursor.getColumnIndex("gender")));
        person.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
        person.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        person.setAvatar(cursor.getString(cursor.getColumnIndex("avatar")));
        return person;
    }
    /*
    * 向用户表中插入数据
     */
    public static void insertPersontb(Person person){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",person.getName());
        contentValues.put("password",person.getPassword());
        contentValues.put("nickname",person.getNickName());
        contentValues.put("gender",person.getGender());
        contentValues.put("birth",person.getBirth());
        contentValues.put("description",person.getDescription());
        contentValues.put("avatar",person.getAvatar());
        db.insert("persontb",null,contentValues);
    }
    /*
    * 判断输入的用户在数据库中是否存在
     */
    public static Boolean JudgePersonExist(String name,String password){
        String sql = "select count(*) from persontb where name=? and password =?";
        Cursor cursor = db.rawQuery(sql,new String[]{name,password});
        int result=0;
        //判断
        if(cursor.moveToFirst()){
            @SuppressLint("Range") int count = cursor.getInt(cursor.getColumnIndex("count(*)"));
            result = count;
        }
        if(result==1) return true;
        else return false;
    }

    /*
    * 根据用户名删除用户表中的用户
     */
    public static int deletePersontb(){
        int i = db.delete("persontb",null,null);
        return i;
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

    //根据isbn号获取Book对象
    public static Book QueryBook(String isbn){
        String sql = "select * from booktb where isbn = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{isbn});
        cursor.moveToFirst();
        Book book = new Book();
        book.setId(cursor.getString(0));
        book.setIsbn10(cursor.getString(0));
        book.setTitle(cursor.getString(1));
        book.setImage(cursor.getString(2));
        book.setAuthor(cursor.getString(3));
        book.setTranslator(cursor.getString(4));
        book.setPublisher(cursor.getString(5));
        book.setPubdate(cursor.getString(6));
        book.setTags(cursor.getString(7));
        book.setBinding(cursor.getString(8));
        book.setPrice(cursor.getString(9));
        book.setPages(cursor.getInt(10));
        book.setAuthor_intro(cursor.getString(11));
        book.setSummary(cursor.getString(12));
        cursor.close();
        return book;
    }

    //更新漂流瓶数据表
    //public static
    //获取根据用户名获取漂流瓶
    public static List<Drift> GetOwnDriftById(int author_id){
        List<Drift> drifts = new ArrayList<>();
        String sql = "select * from drifttb where author_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{author_id+""});
        while(cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String book_author = cursor.getString(cursor.getColumnIndex("book_title"));
            @SuppressLint("Range") String recommend = cursor.getString(cursor.getColumnIndex("recommend"));
            Drift drift = new Drift(id, author_id, time, title,book_author,recommend);
            drifts.add(drift);
        }
        return drifts;
    }

    //获取得到的其他人的漂流瓶
    public static List<Drift> GetOtherDrift(int author_id){
        List<Drift> drifts = new ArrayList<>();
        String sql = "select * from drifttb where author_id != ?";
        Cursor cursor = db.rawQuery(sql, new String[]{author_id+""});
        while(cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String book_author = cursor.getString(cursor.getColumnIndex("book_title"));
            @SuppressLint("Range") String recommend = cursor.getString(cursor.getColumnIndex("recommend"));
            Drift drift = new Drift(id, author_id, time, title,book_author,recommend);
            drifts.add(drift);
        }
        return drifts;
    }

    //插入一条漂流瓶到数据表
    public static void insertOneDriftToDrifttb(Drift drift){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",drift.getId());
        contentValues.put("author_id",drift.getAuthor_id());
        contentValues.put("time",drift.getTime());
        contentValues.put("title",drift.getTitle());
        contentValues.put("book_author",drift.getBook_Author());
        contentValues.put("recommend",drift.getRecommend());
        db.insert("drifttb",null,contentValues);
    }

}
