package com.example.news_application.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news_application.R;
import com.example.news_application.db.DB;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;

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

        roleSpinner = (Spinner) findViewById(R.id.join_roleSpinner);
        idText = (EditText) findViewById(R.id.join_idText);
        nameText = (EditText) findViewById(R.id.join_nameText);
        pwText1 = (EditText) findViewById(R.id.join_pwText1);
        pwText2 = (EditText) findViewById(R.id.join_pwText2);
        phoneText = (EditText) findViewById(R.id.join_phoneText);

        checkBtn = (Button) findViewById(R.id.join_checkBtn);
        joinBtn = (Button) findViewById(R.id.join_joinBtn);

        idText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                builder.setTitle("아이디 중복체크");
                builder.setPositiveButton("확인", null);
                if (idText.getText().equals("")) {
                    builder.setMessage("아이디를 입력해주세요.");
                    builder.create().show();
                    check = false;
                } else if (DB.isDuplicateID(idText.getText().toString())) {
                    // 아이디가 이미 있다
                    builder.setMessage("이미 존재하는 아이디입니다.");
                    builder.create().show();
                    check = false;
                } else {
                    builder.setMessage("사용가능한 아이디입니다.");
                    builder.create().show();
                    check = true;
                }
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                builder.setTitle("회원가입");

                if (! check) {
                    builder.setMessage("아이디 중복체크를 해주세요.");
                    builder.create().show();
                    return;
                }

                if (idText.getText().equals("")
                || pwText1.getText().equals("")
                || pwText2.getText().equals("")
                || nameText.getText().equals("")
                || phoneText.getText().equals("")) {
                    builder.setMessage("모든 양식을 입력해주세요.");
                    builder.create().show();
                    return;
                }
                if (! pwText1.getText().toString().equals(pwText2.getText().toString())) {
                    builder.setMessage("비밀번호를 확인해주세요.");
                    builder.create().show();
                    return;
                }

                Log.d("AAAA", roleSpinner.getSelectedItemPosition() + "");
                if (DB.join(idText.getText().toString(),
                        pwText1.getText().toString(),
                        nameText.getText().toString(),
                        phoneText.getText().toString(),
                        (roleSpinner.getSelectedItemPosition()+1))) {
                    Toast.makeText(JoinActivity.this, "회원가입 성공!!!", Toast.LENGTH_SHORT).show();
                    JoinActivity.this.finish();
                } else {
                    Toast.makeText(JoinActivity.this, "회원가입 실패!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addRole();
    }

    public void addRole() {
        ArrayList<String> roleList = new ArrayList<>();
        roleList.add("고객");
        roleList.add("기자");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, roleList);
        roleSpinner.setAdapter(adapter);
    }
}