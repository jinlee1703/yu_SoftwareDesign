package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_application.R;
import com.example.news_application.obj.Comment;
import com.example.news_application.obj.News;

public class NewsDetailActivity extends AppCompatActivity {
    private News news;
    private TextView title;
    private Button newsReportBtn;
    private ImageView image;
    private TextView contents;
    private Comment[] comment;
    private Button[] commentReportBtn;
    private EditText commentText;
    private Button commentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
    }

    public void setNewsDetail() {

    }

    public void setNewsComments() {

    }

    public void reportNews() {

    }

    public void reportComment(int commentId) {

    }

    public void writeComment() {

    }
}