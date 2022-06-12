package com.example.news_application.activity;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_application.R;
import com.mordred.wordcloud.WordCloud;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class HotKeywordActivity extends AppCompatActivity {
    private TextView dateText;
    private ImageView wordCloud;
    private Map<String, Integer> wordMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_keyword);

        setViewById();

        new Thread(() -> {
            String url = "https://trends.google.com/trends/trendingsearches/daily/rss?geo=KR";

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactoty.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = null;
            try {
                doc = dBuilder.parse(url);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("item");
            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    wordMap.put(getTagValue("title", eElement),
                            Integer.parseInt(getTagValue("ht:approx_traffic", eElement)
                                    .replace(",", "")
                                    .replace("+", "")));
                }
            }
            Message msg = handler.obtainMessage();
            handler.sendMessage(msg);
        }).start();
    }

    public void setViewById() {
        dateText = (TextView) findViewById(R.id.hotkeyword_dateText);
        wordCloud = (ImageView) findViewById(R.id.hotkeyword_imageView);
    }

    public void eventHandler() {

    }

    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            WordCloud wd = new WordCloud(wordMap, 250, 250,Color.BLACK,Color.WHITE);
            wd.setWordColorOpacityAuto(true);
            dateText.setText(new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date()));
            wordCloud.setImageBitmap(wd.generate());
        }
    };
}
