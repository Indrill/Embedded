package com.example.thomas.gymclubapp.DataBase.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDB extends SQLiteOpenHelper {
    private static final int DB_VER = 1;

    private static final String DB_NAME="GymApp";

    public static final String DB_TABLE_COACH="GymCoach";
    public static final String DB_NAME_COACH="Name";
    public static final String DB_DESC_COACH="Desc";
    public static final String DB_PHONE_COACH="Phone";

    public static final String DB_TABLE_NEWS="GymNews";
    public static final String DB_TITLE_NEWS="Title";
    public static final String DB_CONTENT_NEWS="Content";


    public myDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL);", DB_TABLE_COACH, DB_NAME_COACH, DB_DESC_COACH, DB_PHONE_COACH);
        db.execSQL(query);
        query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL);", DB_TABLE_NEWS, DB_TITLE_NEWS, DB_CONTENT_NEWS);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = String.format("DELETE TABLE IF EXIST %s", DB_TABLE_COACH);
        db.execSQL(query);
        query = String.format("DELETE TABLE IF EXIST %s", DB_TABLE_NEWS);
        db.execSQL(query);
        onCreate(db);
    }
}
