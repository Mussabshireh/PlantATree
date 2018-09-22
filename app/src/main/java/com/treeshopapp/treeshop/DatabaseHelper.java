package com.treeshopapp.treeshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DBName="TreeApp";
    private static final int DATABASE_VERSION=1;

    @Override
    public void onCreate(SQLiteDatabase TreeDatabase) {
        TreeDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(30), address VARCHAR (50), password VARCHAR(10) )");
        TreeDatabase.execSQL("CREATE TABLE IF NOT EXISTS tree (_id INTEGER PRIMARY KEY AUTOINCREMENT, tree_name VARCHAR(50), price INTEGER )");
        TreeDatabase.execSQL("CREATE TABLE IF NOT EXISTS orders (_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER NOT NULL, tree_id INTEGER NOT NULL, price INTEGER, the_numer_of_tree INTEGER NOT NULL)");
    };

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS tree");
        db.execSQL("DROP TABLE IF EXISTS orders");
    }

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DATABASE_VERSION);
    }

}
