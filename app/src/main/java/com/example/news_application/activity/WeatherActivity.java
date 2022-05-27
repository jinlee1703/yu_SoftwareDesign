package com.example.news_application.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_application.R;

import org.w3c.dom.Text;

public class WeatherActivity extends AppCompatActivity {
    private TextView dateText;
    private ImageView imageView;
    private TextView explanationText;
    private TextView temperaturesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public void setWeatherInfo() {

    }
}