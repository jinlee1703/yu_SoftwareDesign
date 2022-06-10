package com.example.news_application.obj;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class News {
    private String title;
    private String contents;
    private byte[] image;

    public News(String title, String contents, byte[] image) {
        this.title = title;
        this.contents = contents;

//        Bitmap bitmap = ((BitmapDrawable)image).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        this.image = stream.toByteArray();
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(Drawable image) {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}