package com.example.news_application.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.example.news_application.obj.News;
import com.example.news_application.obj.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

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

        db.execSQL("INSERT INTO news_category ('name') VALUES ('연예')");
        db.execSQL("INSERT INTO news_category ('name') VALUES ('스포츠')");
        db.execSQL("INSERT INTO news_category ('name') VALUES ('정치')");
        db.execSQL("INSERT INTO news_category ('name') VALUES ('경제')");
        db.execSQL("INSERT INTO news_category ('name') VALUES ('사회')");
        db.execSQL("INSERT INTO news_category ('name') VALUES ('생활/문화')");
        db.execSQL("INSERT INTO news_category ('name') VALUES ('IT/과학')");
        db.execSQL("INSERT INTO news_category ('name') VALUES ('세계')");

        dbHelper.close();
        db.close();
    }

    public static boolean login(String id, String pw) {
        if (id.equals("admin") && pw.equals("admin")) {
            loginUser = new User();
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

    public static boolean join(String id, String pw, String name, String phone, int role) {
        try {
            db = dbHelper.getWritableDatabase();
            db.execSQL("INSERT INTO user ('id', 'pw', 'name', 'phone', 'role') VALUES ('" + id + "', '" + pw + "', '" + name + "', '" + phone + "', '" + role + "')");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDuplicateID(String id) {
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE id='" + id + "'", null);

        if (cursor.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<String> getNewsCategory() {
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM news_category", null );

        ArrayList<String> categoryList = new ArrayList<String>();
        while (cursor.moveToNext()) {
            categoryList.add(cursor.getString(1));
        }
        return categoryList;
    }

    public static boolean writeNews(News news) {
        try {
            String title = news.getTitle();
            String contents = news.getContents();
            byte[] image = news.getImage();

            db = dbHelper.getWritableDatabase();
            SQLiteStatement p = db.compileStatement("INSERT INTO news ('title', 'contents', 'image', 'u_no', 'reg_date') VALUES (?, ?, ?, ?, ?)");
            p.bindString(1, title);
            p.bindString(2, contents);
            p.bindBlob(3, image);
            p.bindLong(4, DB.loginUser.getUserNo());
            p.bindString(5, new Date().toString());
            p.executeInsert();
            return true;
        } catch (Exception e) {
            Log.d("AAAAA", e.getMessage());
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
            db.execSQL("CREATE TABLE news_category ('no' INTEGER NOT NULL, 'name' TEXT, PRIMARY KEY('no' AUTOINCREMENT))");
            db.execSQL("CREATE TABLE news ('no' INTEGER NOT NULL, 'title' TEXT, 'contents' TEXT, 'image' BLOB, 'u_no' INTEGER, 'reg_date' TEXT, PRIMARY KEY('no' AUTOINCREMENT))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user");
            db.execSQL("DROP TABLE IF EXISTS hashtag");
            db.execSQL("DROP TABLE IF EXISTS news_category");
            db.execSQL("DROP TABLE IF EXISTS news");
            onCreate(db);
        }
    }
}
