package com.example.news_application.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.news_application.R;
import com.example.news_application.db.DB;
import com.example.news_application.obj.News;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class NewsFormActivity extends AppCompatActivity {
    private Spinner categorySpinner;
    private EditText titleText;
    private EditText contentText;
    private ImageView imageView;
    private Button writeBtn;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_form);

        categorySpinner = (Spinner) findViewById(R.id.newsform_categorySpinner);
        titleText = (EditText) findViewById(R.id.newsform_titleText);
        contentText = (EditText) findViewById(R.id.newsform_contentText);
        imageView = (ImageView) findViewById(R.id.newsform_imageView);
        writeBtn = (Button) findViewById(R.id.newsform_writeBtn);

        setNewsCategory();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                imageView.setImageBitmap();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0);
            }
        });

//        writeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//                if ((! titleText.getText().toString().equals("")) && (! contentText.getText().toString().equals(""))) {
//                    if (DB.writeNews(new News(titleText.getText().toString(), contentText.getText().toString(), stream.toByteArray()))) {
//                        Toast.makeText(NewsFormActivity.this, "작성 완료!!!", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        Toast.makeText(NewsFormActivity.this, "작성 실패!!!", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(NewsFormActivity.this, "제목과 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Glide.with(getApplicationContext()).load(data.getData()).override(imageView.getWidth(), imageView.getHeight()).into(imageView);
            }
        }
    }

    public void setNewsCategory() {
        ArrayList<String> newsCategory = DB.getNewsCategory();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, newsCategory);
        categorySpinner.setAdapter(adapter);
    }
}