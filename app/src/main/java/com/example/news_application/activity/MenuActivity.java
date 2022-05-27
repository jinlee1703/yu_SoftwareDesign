package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.news_application.R;
import com.example.news_application.obj.User;

public class MenuActivity extends AppCompatActivity {
    private User user;
    private TextView title;
    private TextView subtitle;
    private Button[] btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void setBtns() {
        // userId를 통해 사용자, 기자, 관리자 구분
        // 해당 버튼 리스트 출력
    }
}