package com.jnu.booktrace.bean;

import android.graphics.Bitmap;

public class Book {
    private String id;
    private String isbn10;
    private String isbn13;
    private String title;
    private String image;
    private Bitmap image_bitmap;
    private String author;
    private String translator;
    private String publisher;
    private String pubdate;
    private String tags;
    private String binding;
    private String price;
    private int pages;
    private String author_intro;
    private String summary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getBinding() {
        return binding;
    }

    /**
     *
     * @param binding 规格，精装/平装
     */
    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    /**
     *
     * @param pages 页数
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    /**
     *
     * @param author_intro 作者简介
     */
    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    /**
     * @param summary 书籍简介
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Bitmap getImage_bitmap() {
        return image_bitmap;
    }

    public void setImage_bitmap(Bitmap image_bitmap) {
        this.image_bitmap = image_bitmap;
    }
}
