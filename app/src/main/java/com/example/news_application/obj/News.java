package com.example.news_application.obj;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class News {
    private String title;
    private String contents;
    private byte[] image;

    public News(String title, String contents, byte[] image) {
        this.title = title;
        this.contents = contents;
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

    public static void loadNews() throws IOException {
        int page = 5;
        for (int i = 0; i < page; i ++) {
            String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=265&sid1=100&date=20210811&page=" + i;

            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("class", "list_body newsflash_body");

            Element element = elements.get(0);
            Elements photoElements = element.getElementsByAttributeValue("class", "photo");

            for (int j = 0; j < photoElements.size(); j++) {
                Element articleElement = photoElements.get(j);
                Elements aElements = articleElement.select("a");
                Element aElement = aElements.get(0);

                String articleUrl = aElement.attr("href");		// 기사링크

                Element imgElement = aElement.select("img").get(0);
                String imgUrl = imgElement.attr("src");			// 사진링크
                String title = imgElement.attr("alt");			// 기사제목

                Document subDoc = Jsoup.connect(articleUrl).get();
                Element contentElement = subDoc.getElementById("articleBodyContents");
                String content = contentElement.text();			// 기사내용

                System.out.println(title);
                Log.d("title: ", title);
                Log.d("content: ", content);
                Log.d("", "");
            }
        }

    }
}