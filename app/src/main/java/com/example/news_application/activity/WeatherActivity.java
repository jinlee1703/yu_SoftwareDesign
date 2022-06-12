package com.example.news_application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_application.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {
    private TextView dateText;
    private ImageView imageView;
    private TextView explanationText;
    private TextView temperaturesText;
    ActivityHandler handler = new ActivityHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        setViewById();
        WeatherInfoThread thread = new WeatherInfoThread();
        thread.start();
    }

    public void setViewById() {
        dateText = (TextView) findViewById(R.id.weather_dateText);
        imageView = (ImageView) findViewById(R.id.weather_imageView);
        explanationText = (TextView) findViewById(R.id.weather_weatherExplanation);
        temperaturesText = (TextView) findViewById(R.id.weather_temperaturesText);
    }

    class WeatherInfoThread extends Thread {
        @Override
        public void run() {
            try {
                String URL = "https://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query=%EC%84%9C%EC%9A%B8+%EB%82%A0%EC%94%A8";
                Document doc = Jsoup.connect(URL).get();	//URL 웹사이트에 있는 html 코드를 다 끌어오기
                Elements temele = doc.select(".temperature_text");	//끌어온 html에서 클래스네임이 "temperature_text" 인 값만 선택해서 빼오기
                Elements temele_info = doc.select(".before_slash");
                boolean isEmpty = temele.isEmpty(); //빼온 값 null체크
                Log.d("Tag", "isNull? : " + isEmpty); //로그캣 출력
                if(isEmpty == false) { //null값이 아니면 크롤링 실행
                    String tem = temele.get(0).text().substring(5); //크롤링 하면 "현재온도30'c" 이런식으로 뽑아와지기 때문에, 현재온도를 잘라내고 30'c만 뽑아내는 코드
                    Bundle bundle = new Bundle();
                    bundle.putString("temperature", tem); //bundle 이라는 자료형에 뽑아낸 결과값 담아서 main Thread로 보내기
                    bundle.putString("temperature_info", temele_info.get(0).text());
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ActivityHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();                            // new Thread에서 작업한 결과물 받기
            dateText.setText(new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date()));
            temperaturesText.setText(bundle.getString("temperature"));   //받아온 데이터 textView에 출력
            explanationText.setText(bundle.getString("temperature_info"));

            if (explanationText.getText().toString().equals("맑음")) {
                imageView.setImageResource(R.drawable.sunny);
            }else if (explanationText.getText().toString().equals("구름많음")) {
                imageView.setImageResource(R.drawable.cloudy);
            } else if (explanationText.getText().toString().equals("흐리고 비")) {
                imageView.setImageResource(R.drawable.rain);
            } else {
                imageView.setImageResource(R.drawable.etc);
            }
        }
    }
}