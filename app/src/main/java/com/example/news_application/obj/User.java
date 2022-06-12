package com.example.news_application.obj;

import java.util.ArrayList;

public class User {
    private int userNo;
    private String userId;
    private String userName;
    private String userPhone;
    private int userRole;

    public User(int userNo, String userId, String userName, String userPhone, int userRole) {
        this.userNo = userNo;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userRole = userRole;
    }

    public User() {
        this.userNo = 0;
        this.userId = "admin";
        this.userName = "관리자";
        this.userPhone = "";
        this.userRole = 0;
    }

    public int getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserRole() {
        return userRole;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
