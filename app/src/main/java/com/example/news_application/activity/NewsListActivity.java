package com.example.news_application.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.news_application.R;
import com.example.news_application.obj.News;
import com.example.news_application.adapter.NewsAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsListActivity extends AppCompatActivity {
    private Button[] btn = new Button[6];
    private News[] news;
    private ListView listView;
    //    ActivityHandler handler = new ActivityHandler();
    private ArrayList<News> newsArrayList = new ArrayList<News>();
    private NewsAdapter myAdapter;
    int[] btnId = new int[] {
            R.id.newslist_category1,
            R.id.newslist_category2,
            R.id.newslist_category3,
            R.id.newslist_category4,
            R.id.newslist_category5,
            R.id.newslist_category6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        for (int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btnId[i]);
        }

        for (int i = 0; i < 6; i++) {
            int categoryNo = i;

            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewsListLoadThread thread = new NewsListLoadThread(categoryNo - 1);
                    thread.start();
                }
            });
        }

        NewsListLoadThread thread = new NewsListLoadThread(0);
        thread.start();
    }

    class NewsListLoadThread extends Thread {
        private int[] cateogry = new int[] {100, 101, 102, 103, 105, 104};
        private int categoryNo = 0;

        public NewsListLoadThread(int categoryNo) {
            this.categoryNo = categoryNo;
        }

        @Override
        public void run() {
            try {
                int page = 2;
                int cnt = 0;

                newsArrayList.clear();

                for(int j=1; j < page; j++) {
                    String url = "https://news.naver.com/main/list.naver?mode=LSD&mid=shm" +
                            "&sid1=" + cateogry[categoryNo] + "&page=" + j;
                    Document doc = Jsoup.connect(url).get();
                    Elements elements = doc.getElementsByAttributeValue("class", "list_body newsflash_body");

                    Element element = elements.get(0);
                    Elements photoElements = element.getElementsByAttributeValue("class", "photo");

                    for(int i=0; i<photoElements.size(); i++) {
                        Element articleElement = photoElements.get(i);
                        Elements aElements = articleElement.select("a");
                        Element aElement = aElements.get(0);

                        String articleUrl = aElement.attr("href");		// 기사링크

                        Element imgElement = aElement.select("img").get(0);
                        String imgUrl = imgElement.attr("src");			// 사진링크
                        String title = imgElement.attr("alt");			// 기사제목

                        Document subDoc = Jsoup.connect(articleUrl).get();
                        Element contentElement = subDoc.getElementById("dic_area");
                        String content = contentElement.text();			// 기사내용

                        newsArrayList.add(new News(title, content, imgUrl));
                    }
                }

                listView = (ListView) findViewById(R.id.newslist_listView);
                myAdapter = new NewsAdapter(NewsListActivity.this, newsArrayList);

                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            myAdapter.notifyDataSetChanged();
            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent intent = new Intent(NewsListActivity.this, NewsDetailActivity.class);
                    intent.putExtra("title", newsArrayList.get(position).getTitle());
                    intent.putExtra("content", newsArrayList.get(position).getContents());
                    intent.putExtra("imgUrl", newsArrayList.get(position).getImage());
                    startActivity(intent);
                }
            });
        }
    };
}