package com.jnu.booktrace.bean;

public class Drift {
    private int id;
    private int author_id;
    private String time;
    private String book_author;
    private String title;
    private String recommend;

    public Drift() {
    }

    public Drift(int id, int author_id,String time,  String title, String book_author, String recommend) {
        this.id = id;
        this.author_id = author_id;
        this.time = time;
        this.title = title;
        this.book_author = book_author;
        this.recommend = recommend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getBook_Author() {
        return book_author;
    }

    public void setBook_Author(String book_author) {
        this.book_author = book_author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
