package com.example.jeevan.testapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jeevan on 5/20/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String dbName = "TEST_DB";
    private static final int dbVersion = 1;

    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table for Testing DB
        db.execSQL(Test1Emp.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Test1Emp.DELETE_QUERY);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Test1Emp.DELETE_QUERY);
        onCreate(db);
    }
}
