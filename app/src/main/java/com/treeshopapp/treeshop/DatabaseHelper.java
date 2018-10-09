package com.treeshopapp.treeshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DBName="TreeApp2";
    private static final int DATABASE_VERSION=1;


    @Override
    public void onCreate(SQLiteDatabase treeDatabase) {
        treeDatabase.execSQL("CREATE TABLE IF NOT EXISTS Users (_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(30), password VARCHAR(10), emailaddress VARCHAR (50) )");
        treeDatabase.execSQL("CREATE TABLE IF NOT EXISTS Tree (_id INTEGER PRIMARY KEY AUTOINCREMENT, tree_name VARCHAR(50), price INTEGER, image tree_photo )");
        treeDatabase.execSQL("CREATE TABLE IF NOT EXISTS Orders (_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER NOT NULL, tree_id INTEGER NOT NULL, price INTEGER, the_numer_of_tree INTEGER NOT NULL)");
    };

    @Override
    public void onUpgrade(SQLiteDatabase treeDatabase, int i, int i1) {
        treeDatabase.execSQL("DROP TABLE IF EXISTS Users");
        treeDatabase.execSQL("DROP TABLE IF EXISTS Tree");
        treeDatabase.execSQL("DROP TABLE IF EXISTS Orders");
    }

    public boolean registerUser(String username, String password, String email){
        SQLiteDatabase treeDatabase = this.getWritableDatabase();//sqllite database object
        ContentValues contentValues = new ContentValues(); //helps write to the database
        contentValues.put("username",username);
        contentValues.put("emailaddress",email);
        contentValues.put("password",password);



        long result = treeDatabase.insert("Users", null,contentValues);

        if(result == -1)
        {
            return false;
        }
        else{
            return true;
        }


    }


    public boolean userAuthentication(String email, String password)
    {
        //Readable function allows the reading of database values
        SQLiteDatabase treeDatabase = this.getReadableDatabase();
       Cursor check = treeDatabase.rawQuery("SELECT emailaddress , password FROM Users WHERE emailaddress='"+ email + "'" + "AND password='"+password+"'",null);

        String tempU,tempP;
        boolean authentication = false ;

       if(check.moveToFirst())
       {
           do{

               tempU = check.getString(0);
               tempP = check.getString(1);

               if(tempU.equals(email)&& tempP.equals(password))
               {
                   authentication =  true;
                   break;
               }
               else{
                   authentication = false;
                   break;
               }


           }while(check.moveToNext());

           check.close();
       }

    //returns if true or false if user has access to the requested account
      return authentication;
    }

    public boolean CheckIsDataAlreadyInDBorNot(String email) {
        SQLiteDatabase treeDatabase = this.getReadableDatabase();

        String Query = "Select * from " + "Users" + " where " + "emailaddress" + " = " + email;
        Cursor cursor = treeDatabase.rawQuery("SELECT emailaddress FROM Users WHERE emailaddress='"+ email + "'",null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;


    }






    public DatabaseHelper(Context context) {
        super(context, DBName, null, DATABASE_VERSION);
    }

}
