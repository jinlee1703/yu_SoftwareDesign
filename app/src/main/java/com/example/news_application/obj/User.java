package com.example.news_application.obj;

import java.util.ArrayList;

public class User {
    private int userNo;
    private String userId;
    private String userName;
    private String userPhone;
    private int userRole;
    private ArrayList<String> hashtag;

    public User(int userNo, String userId, String userName, String userPhone, int userRole, ArrayList<String> hashtag) {
        this.userNo = userNo;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userRole = userRole;
        this.hashtag = hashtag;
    }

    public User() {
        this.userNo = 0;
        this.userId = "admin";
        this.userName = "관리자";
        this.userPhone = "";
        this.userRole = 0;
        this.hashtag = null;
    }

    public int getUserNo() {
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
