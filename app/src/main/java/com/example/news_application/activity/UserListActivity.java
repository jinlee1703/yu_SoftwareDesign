package com.example.news_application.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.news_application.R;
import com.example.news_application.adapter.UserAdapter;
import com.example.news_application.db.DB;
import com.example.news_application.obj.User;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    private ArrayList<User> userArrayList;
    private UserAdapter myAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        setViewById();
        setListView();
    }

    public void setViewById() {
        listView = (ListView) findViewById(R.id.userlist_listView);
    }

    public void setListView() {
        userArrayList = DB.getUserLIst();
        myAdapter = new UserAdapter(UserListActivity.this, userArrayList);

        myAdapter.notifyDataSetChanged();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(UserListActivity.this)
                        .setTitle("회원 탈퇴")
                        .setMessage(userArrayList.get(position).getUserName() + " 회원을 탈퇴시키겠습니까?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (DB.withdrawalUser(userArrayList.get(position).getUserNo())) {
                                    Toast.makeText(UserListActivity.this, "회원 탈퇴 완료!!!", Toast.LENGTH_SHORT).show();
                                    userArrayList.clear();
                                    setListView();
                                    myAdapter.notifyDataSetChanged();
                                    listView.setAdapter(myAdapter);
                                } else {
                                    Toast.makeText(UserListActivity.this, "회원 탈퇴 실패!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
                AlertDialog msgDlg = msgBuilder.create();
                msgDlg.show();
            }
        });
    }
}