package com.example.news_application.activity;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news_application.R;
import com.example.news_application.db.DB;

public class LoginActivity extends AppCompatActivity {
    private EditText idText;
    private EditText pwText;
    private Button loginBtn;
    private Button joinBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DB.init(this);

        // findViewById
        idText = (EditText) findViewById(R.id.login_idText);
        pwText = (EditText) findViewById(R.id.login_pwText);
        loginBtn = (Button) findViewById(R.id.login_loginBtn);
        joinBtn = (Button) findViewById(R.id.login_joinBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DB.login(idText.getText().toString(), pwText.getText().toString())) {
                    // 로그인 성공
                    Toast.makeText(LoginActivity.this, "로그인 성공!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 실패
                    Toast.makeText(LoginActivity.this, "로그인 실패!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}