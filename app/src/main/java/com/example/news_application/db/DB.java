package com.example.news_application.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.news_application.obj.User;

import java.util.ArrayList;

public class DB {
    public static DBHelper dbHelper;
    public static SQLiteDatabase db;
    public static User loginUser;

    public static void init(Context context) {
        dbHelper = new DBHelper(context);

        db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 2);

        db.execSQL("INSERT INTO user ('id', 'pw', 'name', 'phone') VALUES ('id1', 'pw1', '사용자1', '010-0000-0000')");
        db.execSQL("INSERT INTO user ('id', 'pw', 'name', 'phone') VALUES ('id2', 'pw2', '사용자2', '010-0000-0000')");

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
                    loginUser = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean join(String id, String pw, String name, String phone) {
        try {
            db = dbHelper.getWritableDatabase();
            db.execSQL("INSERT INTO user ('id', 'pw', 'name', 'phone') VALUES ('" + id + "', '" + pw + "', '" + name + "', '" + phone + "')");
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

    public static boolean updateUserInfo(String name, String pw, String phone) {
        try {
            db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("pw", pw);
            contentValues.put("name", name);
            contentValues.put("phone", phone);

            db.update("user", contentValues, "no = ?", new String[]{Integer.toString(loginUser.getUserNo())});
            login(loginUser.getUserId(), pw);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean withdrawalUser(int userNo) {
        try {
            db = dbHelper.getWritableDatabase();
            db.delete("user","no = ?", new String[] {Integer.toString(userNo)});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<User> getUserLIst() {
        ArrayList<User> list = new ArrayList<User>();
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null );
        while (cursor.moveToNext()) {
            list.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(5)));
        }
        return list;
    }

    static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "swd_db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE user ('no' INTEGER NOT NULL, 'id' TEXT, 'pw' TEXT, 'name' TEXT, 'phone' TEXT, PRIMARY KEY ('no' AUTOINCREMENT) )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user");
            onCreate(db);
        }
    }
}
