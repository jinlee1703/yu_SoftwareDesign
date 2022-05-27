package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.news_application.R;
import com.google.android.material.chip.Chip;

public class HashtagActivity extends AppCompatActivity {
    private EditText hashtagText;
    private Button setBtn;
    private Chip[] chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag);
    }

    public void setHashtag() {

    }

    public void deleteHashtag(int hashtagId) {

    }
}