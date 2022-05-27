package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.news_application.R;

public class NotificationActivity extends AppCompatActivity {
    private TextView[] hashtagText;
    private TextView[] titleText;
    private TextView[] dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void setNotificationList() {

    }

    public void selectNews(int newsId) {

    }
}