package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.news_application.R;

public class UserListActivity extends AppCompatActivity {
    private EditText[] idText;
    private EditText[] roleText;
    private Button[] deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
    }

    public void setUserList() {

    }

    public void withdrawalUser(String userId) {

    }
}