package com.example.news_application.obj;

import android.widget.ImageView;
import android.widget.TextView;

public class News {
    private String image;
    private String title;
    private String contents;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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