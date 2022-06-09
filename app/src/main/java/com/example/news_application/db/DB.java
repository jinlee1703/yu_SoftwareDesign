package com.example.news_application.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.news_application.obj.User;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
    public static DBHelper dbHelper;
    public static SQLiteDatabase db;
    public static User loginUser;

    public static void init(Context context) {
        dbHelper = new DBHelper(context);

        db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 2);

        db.execSQL("INSERT INTO user ('id', 'pw', 'name', 'phone', 'role') VALUES ('id1', 'pw1', '사용자1', '010-0000-0000', '1')");
        db.execSQL("INSERT INTO user ('id', 'pw', 'name', 'phone', 'role') VALUES ('id2', 'pw2', '기자1', '010-0000-0000', '2')");

        Cursor cursor = db.rawQuery("SELECT * FROM user", null );
        while (cursor.moveToNext()) {
            Toast.makeText(context, cursor.getString(1), Toast.LENGTH_SHORT).show();
        }

        dbHelper.close();
        db.close();
    }

    public static boolean login(String id, String pw) {
        if (id.equals("admin") && pw.equals("admin")) {
            loginUser = new User(0);
            return true;
        } else {
            db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM user", null );
            while (cursor.moveToNext()) {
                if (cursor.getString(1).equals(id) && cursor.getString(2).equals(pw)) {
                    // 로그인 유저 객체 생성
                    ArrayList<String> tagList = new ArrayList<String>();
                    Cursor tagCursor = db.rawQuery("SELECT * FROM hashtag WHERE u_no = '" + cursor.getInt(0) + "'", null);
                    while (tagCursor.moveToNext()) {
                        tagList.add(cursor.getString(2));
                    }
                    loginUser = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getInt(5), tagList);

                    return true;
                }
            }
            return false;
        }
    }

    static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "swd_db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE user ('no' INTEGER NOT NULL, 'id' TEXT, 'pw' TEXT, 'name' TEXT, 'phone' TEXT, 'role' INTEGER, PRIMARY KEY ('no' AUTOINCREMENT) )");
            db.execSQL("CREATE TABLE hashtag ('no' INTEGER NOT NULL, 'u_no' INTEGER , 'tag' TEXT, FOREIGN KEY('u_no') REFERENCES 'user'('no') ON DELETE SET NULL ON UPDATE CASCADE, PRIMARY KEY('no' AUTOINCREMENT) )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user");
            db.execSQL("DROP TABLE IF EXISTS hashtag");
            onCreate(db);
        }
    }
}
