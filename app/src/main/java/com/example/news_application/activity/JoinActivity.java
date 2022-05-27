package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.news_application.R;

public class JoinActivity extends AppCompatActivity {
    private Spinner roleSpinner;
    private EditText idText;
    private EditText nameText;
    private EditText pwText1;
    private EditText pwText2;
    private EditText phoneText;
    private Button checkBtn;
    private Button joinBtn;
    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
    }

    public void idCheck() {

    }

    public void join() {

    }

    public boolean isChecked() {
        return check;
    }

    public void setChecked(boolean check) {
        check = check;
    }
}