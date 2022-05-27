package com.example.news_application;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.news_application.obj.News;

public class NewsFormActivity extends AppCompatActivity {
    private EditText titleText;
    private EditText contentText;
    private Button imgBtn;
    private Button writeBtn;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_form);
    }

    public void findImage() {

    }

    public void writeNews() {

    }
}