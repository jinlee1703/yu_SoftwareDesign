package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news_application.R;

public class NewsDetailActivity extends AppCompatActivity {
    private News news;
    private TextView title;
//    private Button newsReportBtn;
    private ImageView image;
    private TextView contents;
//    private Comment[] comment;
//    private Button[] commentReportBtn;
//    private EditText commentText;
//    private Button commentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        setViewById();
        setView();
    }

    public void setViewById() {
        title = (TextView) findViewById(R.id.newsdetail_newsTitle);
        contents = (TextView) findViewById(R.id.newsdetail_newsContents);
        image = (ImageView) findViewById(R.id.newsdetail_newsImage);
    }

    public void setView() {
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        contents.setText(intent.getStringExtra("content"));
        Glide.with(this).load(intent.getStringExtra("imgUrl"))
                .error(R.drawable.default_news_img)
                .into(image);
    }
}