package com.example.news_application.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news_application.R;
import com.example.news_application.db.DB;
import com.example.news_application.obj.User;

public class UserInfoActivity extends AppCompatActivity {
    private EditText idText;
    private EditText nameText;
    private EditText pwText1;
    private EditText pwText2;
    private EditText phoneText;
    private int[] btnId = {R.id.userinfo_updateBtn, R.id.userinfo_withdrawalBtn, R.id.userinfo_myTraceBtn, R.id.userinfo_setHashtagBtn};
    private Button[] btn = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        idText = (EditText) findViewById(R.id.userinfo_idText);
        nameText = (EditText) findViewById(R.id.userinfo_nameText);
        pwText1 = (EditText) findViewById(R.id.userinfo_pwText1);
        pwText2 = (EditText) findViewById(R.id.userinfo_pwText2);
        phoneText = (EditText) findViewById(R.id.userinfo_phoneText);

        for (int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btnId[i]);
        }

        if (DB.loginUser.getUserRole() == 0) {
            btn[3].setVisibility(View.INVISIBLE);
        }

        getUserInfo();

        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pwText1.getText().toString().equals("")
                    || pwText2.getText().toString().equals("")
                    || nameText.getText().toString().equals("")
                    || phoneText.getText().toString().equals("")) {
                    Toast.makeText(UserInfoActivity.this, "모든 양식을 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (! pwText1.getText().toString().equals(pwText2.getText().toString())) {
                    Toast.makeText(UserInfoActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (DB.updateUserInfo(nameText.getText().toString(), pwText1.getText().toString(), phoneText.getText().toString())) {
                    Toast.makeText(UserInfoActivity.this, "회원 정보 수정 성공!!!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UserInfoActivity.this, "회원 정보 수정 실패!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(UserInfoActivity.this)
                        .setTitle("회원 탈퇴")
                        .setMessage("정말 탈퇴하시겠습니까?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (DB.withdrawalUser(DB.loginUser.getUserNo())) {
                                    Toast.makeText(UserInfoActivity.this, "회원 탈퇴 완료!!!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(UserInfoActivity.this, "회원 탈퇴 실패!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
                AlertDialog msgDlg = msgBuilder.create();
                msgDlg.show();
            }
        });

        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoActivity.this, MyTraceActivity.class);
                startActivity(intent);
            }
        });

        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void getUserInfo() {
        idText.setText(DB.loginUser.getUserId());
        nameText.setText(DB.loginUser.getUserName());
        phoneText.setText(DB.loginUser.getUserPhone());
    }

    public void selectTracetList() {

    }

    public void setHashtag() {

    }
}