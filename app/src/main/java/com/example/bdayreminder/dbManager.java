package com.example.bdayreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbManager extends SQLiteOpenHelper {
private static String dbname ="reminderdb";

public dbManager(@Nullable Context context){
    super(context,dbname,null,1);

}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query ="CREATE table tbl_reminder(id integer primary key autoincrement,title text,date text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    String query = "DROP table if exists tbl_reminder";
    sqLiteDatabase.execSQL(query);
    onCreate(sqLiteDatabase);
    }

    public String addreminder(String title, String date) {
    SQLiteDatabase database=this.getReadableDatabase();

        ContentValues contentValues =new ContentValues();
        contentValues.put("title",title);
        contentValues.put("date",date);

        float result =database.insert("tbl_reminder",null,contentValues);

        if (result==-1){
            return "Failed";
        }
        else{
            return "Successfully inserted";
        }
    }

    public Cursor readallreminders() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query ="select title,date from tbl_reminder order by id desc";
        Cursor cursor=database.rawQuery(query,null);
        return cursor;
    }
}
