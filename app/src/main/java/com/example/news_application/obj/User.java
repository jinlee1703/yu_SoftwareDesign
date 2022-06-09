package com.example.news_application.obj;

import java.util.ArrayList;

public class User {
    private int userNo;
    private String userId;
    private String userName;
    private int userRole;
    private ArrayList<String> hashtag;

    public User(int userNum, String userId, String userName, int userRole, ArrayList<String> hashtag) {
        this.userNo = userNo;
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
        this.hashtag = hashtag;
    }

    public User(int role) {
        this.userNo = 0;
        this.userId = "admin";
        this.userName = "관리자";
        this.userRole = 0;
        this.hashtag = null;
    }

    public int getUserNum() {
        return userNo;
    }

    public void setUserNum(int userNum) {
        this.userNo = userNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public ArrayList<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(ArrayList<String> hashtag) {
        this.hashtag = hashtag;
    }
}
