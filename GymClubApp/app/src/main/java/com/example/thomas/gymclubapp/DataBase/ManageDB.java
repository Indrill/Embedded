package com.example.thomas.gymclubapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thomas.gymclubapp.DataBase.Model.myDB;
import com.example.thomas.gymclubapp.Models.Coach;
import com.example.thomas.gymclubapp.Models.News;

import java.util.ArrayList;

public class ManageDB {
    private myDB mydb;

    public ManageDB(Context context) {
        mydb = new myDB(context);
    }

    public void addCoach(Coach newCoach) {
        if (checkIfExist(newCoach)){
            return;
        }
        else {
            SQLiteDatabase db = mydb.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(myDB.DB_NAME_COACH, newCoach.getName());
            values.put(myDB.DB_DESC_COACH, newCoach.getDescription());
            values.put(myDB.DB_PHONE_COACH, newCoach.getPhoneNbr());
            db.insertWithOnConflict(myDB.DB_TABLE_COACH, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            db.close();
        }
    }

    public void deleteCoach(Coach delCoach){
        SQLiteDatabase db = mydb.getWritableDatabase();
        db.delete(myDB.DB_TABLE_COACH, myDB.DB_NAME_COACH + " LIKE \"" + delCoach.getName() + "\" AND "
                        + myDB.DB_DESC_COACH + " LIKE \"" + delCoach.getDescription() + "\" AND " + myDB.DB_PHONE_COACH + " LIKE \"" + delCoach.getPhoneNbr() + "\"" ,null);
        db.close();
    }

    public ArrayList<Coach> getCoachList(){
        ArrayList<Coach> coachList = new ArrayList<>();
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor cursor = db.query(myDB.DB_TABLE_COACH, new String[] {myDB.DB_NAME_COACH, myDB.DB_DESC_COACH, mydb.DB_PHONE_COACH}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int indexName = cursor.getColumnIndex(myDB.DB_NAME_COACH);
            int indexDesc = cursor.getColumnIndex(myDB.DB_DESC_COACH);
            int indexPhone = cursor.getColumnIndex(myDB.DB_PHONE_COACH);
            coachList.add(new Coach(cursor.getString(indexName), cursor.getString(indexDesc), cursor.getString(indexPhone)));
        }
        cursor.close();
        db.close();
        return coachList;
    }

    public boolean checkIfExist(Coach newCoach) {
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + myDB.DB_TABLE_COACH + " WHERE " + myDB.DB_NAME_COACH + " LIKE \"" + newCoach.getName() + "\" AND "
                                + myDB.DB_DESC_COACH + " LIKE \"" + newCoach.getDescription() + "\" AND " + myDB.DB_PHONE_COACH + " LIKE \"" + newCoach.getPhoneNbr() + "\";", null);
        if (c.getCount() != 0){
            c.close();
            db.close();
            return true;
        }
        c.close();
        db.close();
        return false;
    }

    public void addNew(News newNew) {
        if (checkIfExist(newNew)){
            return;
        }
        else {
            SQLiteDatabase db = mydb.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(myDB.DB_TITLE_NEWS, newNew.getTitle());
            values.put(myDB.DB_CONTENT_NEWS, newNew.getContent());
            db.insertWithOnConflict(myDB.DB_TABLE_NEWS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            db.close();
        }
    }

    public void deleteNew(News delNew){
        SQLiteDatabase db = mydb.getWritableDatabase();
        db.delete(myDB.DB_TABLE_COACH,myDB.DB_TITLE_NEWS + " LIKE \"" + delNew.getTitle() + "\" AND "
                + myDB.DB_CONTENT_NEWS + " LIKE \"" + delNew.getContent() + "\";" ,null);
        db.close();
    }

    public ArrayList<News> getNewsList(){
        ArrayList<News> newsList = new ArrayList<>();
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor cursor = db.query(myDB.DB_TABLE_NEWS, new String[] {myDB.DB_TITLE_NEWS, myDB.DB_CONTENT_NEWS}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int indexTitle = cursor.getColumnIndex(myDB.DB_TITLE_NEWS);
            int indexContent = cursor.getColumnIndex(myDB.DB_CONTENT_NEWS);
            newsList.add(new News(cursor.getString(indexTitle), cursor.getString(indexContent)));
        }
        cursor.close();
        db.close();
        return newsList;
    }

    public boolean checkIfExist(News newNew) {
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + myDB.DB_TABLE_NEWS + " WHERE " + myDB.DB_TITLE_NEWS + " LIKE \"" + newNew.getTitle() + "\" AND "
                + myDB.DB_CONTENT_NEWS + " LIKE \"" + newNew.getContent() + "\";", null);
        if (c.getCount() != 0){
            c.close();
            db.close();
            return true;
        }
        c.close();
        db.close();
        return false;
    }
}
