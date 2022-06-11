package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.news_application.R;

public class MyTraceActivity extends AppCompatActivity {
    private TextView[] dateText;
    private TextView[] traceText;
    private Button[] daleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trace);

//        dateText;
//        traceText;
//        daleteBtn;

//        Cursor cursor = queryData
    }

    public void setTraceList() {
        // userId를 통해 사용자, 기자 구분
        // 사용자일 경우 댓글, 기자일 경우 뉴스 리스트 출력
    }

    public void deleteTrace(int traceId) {

    }
}