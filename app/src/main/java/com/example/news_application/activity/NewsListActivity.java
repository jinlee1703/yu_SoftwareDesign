package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.news_application.R;
import com.example.news_application.obj.News;

import java.util.Date;

public class NewsListActivity extends AppCompatActivity {
    private Button[] btn;
    private News[] news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
    }

    public void setNewsList(int[] categoryId, Date startDate, Date endDate, int pressId) {

    }

    public void selectNews(int newsId) {

    }
}