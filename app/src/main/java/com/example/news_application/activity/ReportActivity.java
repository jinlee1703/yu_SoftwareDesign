package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.news_application.R;

public class ReportActivity extends AppCompatActivity {
    private TextView[] newsTitleText;
    private TextView[] newsDateText;
    private TextView[] newsCntText;
    private Button[] newsDeleteBtn;

    private TextView[] commentIdText;
    private TextView[] commentDateText;
    private TextView[] commentCntText;
    private Button[] commentDeleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    public void setNewsList() {

    }

    public void setCommentList() {

    }

    public void deleteNews(int newsId) {

    }

    public void deleteComment(int commentId) {

    }
}