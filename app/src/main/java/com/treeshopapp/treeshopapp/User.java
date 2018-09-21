package com.treeshopapp.treeshopapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class User {

    private SQLiteDatabase db;
    private DatabaseHelper helper;
    private Context context;

    public User(Context context){
        this.context=context;
        this.helper=new DatabaseHelper(context);

        try{
            open();
        }catch (SQLException e){
            Log.e("User: ", "Exception: "+e.getMessage());
        }
    }

    public void open() throws  SQLException{
        db=helper.getWritableDatabase();
    }

//    public User getUserByID(long ID){
//        Cursor c=db.rawQuery("SELECT username, address FROM users", null);
//        c.moveToFirst();
//        while(c !=null){
//            c.moveToNext();
//        }
//
//    }


    public void close(){
        helper.close();
    }
}
