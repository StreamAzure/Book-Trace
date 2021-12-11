package com.jnu.booktrace.bean;

/*
* @作用：用户个人信息类
* @功能：保存用户的个人信息
**/
public class Person {
    private int id;
    private String name;
    private String password;
    private String nickName;
    private String description;

    public Person() { }

    public Person(int id, String name, String password, String nickName, String description) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
