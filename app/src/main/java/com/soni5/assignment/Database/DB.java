package com.soni5.assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {


    Context context;
    public static  final  String  Database_name  =  "Jobmatch.db";
    public static final int Database_version = 1;

    static final String TABLE_NAME = "Download";
    static final String COLUMN_JOB = "job";
    static final String COLUMN_ADDRESS = "Adress";
    static final String COLUMN_LINK = "Link";
    static final String COLUMN_NAME = "Name";
    static final String COLUMN_EMAIL = "Email";

    public DB(@Nullable Context context ) {
        super(context,Database_name,null,Database_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_JOB + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_LINK + " TEXT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT" +
                ")";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addData(String job, String address, String link, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JOB, job);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LINK, link);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        long newRowId = db.insert(TABLE_NAME, null, values);
        if(newRowId == -1)
        {
            Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Download Sucessfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return newRowId;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}
