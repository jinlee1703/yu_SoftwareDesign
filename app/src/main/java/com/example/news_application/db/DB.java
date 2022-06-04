package com.example.news_application.db;

import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.Statement;

public class DB {
    public static Connection conn;
    public static Statement stmt;

    public static SQLiteDatabase database;

    public static void init() {
        database = open
    }
}
