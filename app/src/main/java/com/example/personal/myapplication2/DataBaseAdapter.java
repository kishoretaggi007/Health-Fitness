package com.example.personal.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseAdapter
{
    static final String DATABASE_NAME = "INFO";
    static final String DATABASE_TABLE ="LOGIN";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table if not exists " +DATABASE_TABLE+" (ID integer primary key autoincrement, NAME text, EMAIL text, MOBILE integer, PASSWORD text, HEIGHT real, WEIGHT real, BLOODGROUP text, AGE integer);";
    public  SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    public  DataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  DataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String NAME,String EMAIL,String MOBILE,String PASSWORD,String HEIGHT,String WEIGHT,String BLOODGROUP,String AGE)
    {
        Log.i("msg","entered here");
        ContentValues newValues = new ContentValues();
        newValues.put("NAME", NAME);
        newValues.put("EMAIL",EMAIL);
        newValues.put("MOBILE",MOBILE);
        newValues.put("PASSWORD",PASSWORD);
        newValues.put("HEIGHT",HEIGHT);
        newValues.put("WEIGHT",WEIGHT);
        newValues.put("BLOODGROUP",BLOODGROUP);
        newValues.put("AGE",AGE);
        db.insert("LOGIN", null, newValues);
    }
    public int deleteEntry(String UserName)
    {
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String NAME)
    {
        Cursor cursor=db.query("LOGIN", null, " NAME=?", new String[]{NAME}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public void  updateEntry(String NAME,String EMAIL,String MOBILE,String PASSWORD,String HEIGHT,String WEIGHT,String BLOODGROUP,String AGE)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("NAME", NAME);
        updatedValues.put("EMAIL",EMAIL);
        updatedValues.put("MOBILE",MOBILE);
        updatedValues.put("PASSWORD",PASSWORD);
        updatedValues.put("HEIGHT",HEIGHT);
        updatedValues.put("WEIGHT",WEIGHT);
        updatedValues.put("BLOODGROUP",BLOODGROUP);
        updatedValues.put("AGE",AGE);


        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{NAME});
    }
}
