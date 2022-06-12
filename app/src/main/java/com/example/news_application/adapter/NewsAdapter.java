package com.example.news_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news_application.R;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<News> newsList;

    public NewsAdapter(Context mContext, ArrayList<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.news_item, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.poster);
        TextView title = (TextView)view.findViewById(R.id.newsTitle);
        TextView content = (TextView)view.findViewById(R.id.contents);

        Glide.with(view).load(newsList.get(position).getImage())
                .error(R.drawable.default_news_img)
                .into(imageView);
        title.setText(newsList.get(position).getTitle());
        content.setText(newsList.get(position).getContents());
        if (newsList.get(position).getTitle().length() > 30) {
            title.setText(newsList.get(position).getTitle().substring(0, 30) + "...");
        }
        if (newsList.get(position).getContents().length() > 30) {
            content.setText(newsList.get(position).getContents().substring(0, 30) + "...");
        }

        return view;
    }
}
