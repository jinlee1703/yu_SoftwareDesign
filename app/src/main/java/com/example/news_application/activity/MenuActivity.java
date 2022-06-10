package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news_application.R;
import com.example.news_application.db.DB;
import com.example.news_application.obj.User;

public class MenuActivity extends AppCompatActivity {
    private TextView title;
    private TextView subtitle;
    private Button[] btn = new Button[5];
    private int[] btnId = new int[]{R.id.menu_btn1, R.id.menu_btn2, R.id.menu_btn3, R.id.menu_btn4, R.id.menu_btn5};
    private String[][] btnText = new String[][]{{"신고 조회", "회원 관리"}, {"뉴스", "날씨", "오늘의 핫키워드", "내 정보", "내 알림"}, {"뉴스 작성", "내 정보"}};
    private AppCompatActivity[][] activities =  new AppCompatActivity[][]{{new ReportActivity(), new UserListActivity()}, {new NewsListActivity(), new WeatherActivity(), new HotKeywordActivity(), new UserInfoActivity(), new MyTraceActivity()}, {new NewsFormActivity(), new MyTraceActivity()}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        title = (TextView) findViewById(R.id.menu_title);
        subtitle = (TextView) findViewById(R.id.menu_subtitle);
        for (int i = 0; i < 5; i ++) {
            btn[i] = (Button) findViewById(btnId[i]);
        }
        setTextViews(DB.loginUser.getUserRole());
        setButtons(DB.loginUser.getUserRole());

        Toast.makeText(this, ReportActivity.class.getName(), Toast.LENGTH_SHORT).show();
    }

    public void setTextViews(int role) {
        // 타이틀 및 회원 이름 출력
        if (role == 0) {
            // 관리자 로그인
            title.setText("Admin Menu");
        } else if (role == 1) {
            // 사용자 로그인
            title.setText("Customer Menu");
        } else if (role == 2) {
            // 기자 로그인
            title.setText("Reporter Menu");
        }
        subtitle.setText(DB.loginUser.getUserName() + "님 반갑습니다.");
    }

    public void setButtons(int role) {
        // 버튼 텍스트 설정
        for (int i = 0; i < btnText[role].length; i++) {
            btn[i].setText(btnText[role][i]);
        }
        // 버튼 출력 여부 결정
        for (int i = btnText[role].length; i < 5; i++) {
            btn[i].setVisibility(View.INVISIBLE);
        }
        // 버튼 이벤트 추가(해당 액티비치 출력)
        for (int i = 0; i < activities[role].length; i++) {
            AppCompatActivity a = activities[role][i];

            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MenuActivity.this, a.getClass());
                    startActivity(intent);
                }
            });
        }
    }
}