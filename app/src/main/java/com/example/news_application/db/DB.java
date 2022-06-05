package com.example.news_application.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class DB {
    public static Connection conn;
    public static Statement stmt;
    public static DBHelper dbHelper;
    public static SQLiteDatabase db;

    public static void init(Context context) {
        dbHelper = new DBHelper(context);

        db = dbHelper.getWritableDatabase();



        dbHelper.onUpgrade(db, 1, 2);

        db.execSQL("INSERT INTO user ('id', 'pw', 'name', 'phone', 'role') VALUES ('id1', 'pw1', '이름1', '010-0000-0000', '0')");

        Cursor cursor = db.rawQuery("SELECT * FROM user", null );
        while (cursor.moveToNext()) {
            Toast.makeText(context, cursor.getString(1), Toast.LENGTH_SHORT).show();
        }

        dbHelper.close();
        db.close();
    }

    static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "swd_db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE user ('num' INTEGER NOT NULL, 'id' TEXT, 'pw' TEXT, 'name' TEXT, 'phone' TEXT, 'role' INTEGER, PRIMARY KEY ('num' AUTOINCREMENT) );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user");
            onCreate(db);
        }
    }
}
