package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_application.R;

public class HotKeywordActivity extends AppCompatActivity {
    private TextView dateText;
    private ImageView wordCloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_keyword);
    }

    public void setWordCloud() {

    }
}