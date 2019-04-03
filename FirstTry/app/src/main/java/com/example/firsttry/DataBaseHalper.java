package com.example.firsttry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

public class DataBaseHalper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "register";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "FirstName";
    public static final String COL_2 = "SecondName";
    public static final String COL_3 = "Password";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "MOL";
    public static final String COL_6 = "COM";

    public DataBaseHalper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE%s(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,SecondName,Password TEXT,Email TEXT,Mol TEXT)", TABLE_NAME));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS%s", TABLE_NAME));
        onCreate(db);
    }
}
