package com.example.news_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.news_application.R;
import com.example.news_application.obj.User;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<User> userList;

    public UserAdapter(Context mContext, ArrayList<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.user_item, null);

        TextView idText = (TextView)view.findViewById(R.id.userId);
        TextView nameText = (TextView)view.findViewById(R.id.userName);
        TextView phoneText = (TextView)view.findViewById(R.id.userPhone);

        idText.setText(userList.get(position).getUserId());
        nameText.setText(userList.get(position).getUserName());
        phoneText.setText(userList.get(position).getUserPhone());

        return view;
    }
}
