package com.example.jeevan.testapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 5/20/17.
 */

public class Transactions {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;
    private static Transactions instance;
    private String TAG = "TRANSACTIONS";

    private Transactions(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context, null);
        this.db = dbHelper.getWritableDatabase();
    }

    public static Transactions getInstance(Context context) {
        if (instance == null) {
            instance = new Transactions(context);
        }
        return instance;
    }

    public com.example.jeevan.testapp.local_models.Test getRecord(String name) {
        // query the db for the name, and return the record
        String selection = Test.NAME + " = ?";
        String[] args = {name};
        Cursor cursor = db.query(Test.TABLE_NAME, null, selection, args, null, null, null);
        if (cursor != null && cursor.getCount() == 1) {
            cursor.moveToNext();
            return fillRecord(cursor);
        }
        return null;
    }

    public List<com.example.jeevan.testapp.local_models.Test> getAllRecords() {
        Cursor cursor = db.query(Test.TABLE_NAME, null, null, null, null, null, null);
        List<com.example.jeevan.testapp.local_models.Test> records = new ArrayList<>();
        while (cursor != null && cursor.moveToNext()) {
            records.add(fillRecord(cursor));
        }
        return records;
    }

    public boolean saveRecord (com.example.jeevan.testapp.local_models.Test record) throws SQLException {
        // save the record
        ContentValues values = new ContentValues();
        Log.d(TAG, record.toString());
        values.put(Test.NAME, record.getName());
        values.put(Test.DESIGNATION, record.getDesignation());
        values.put(Test.PHONE, record.getPhone());
        return db.insertOrThrow(Test.TABLE_NAME, null, values) != -1;
    }

    private com.example.jeevan.testapp.local_models.Test fillRecord(Cursor cursor) {
        com.example.jeevan.testapp.local_models.Test test = new com.example.jeevan.testapp.local_models.Test();
        test.setName(cursor.getString(cursor.getColumnIndex(Test.NAME)));
        test.setDesignation(cursor.getString(cursor.getColumnIndex(Test.DESIGNATION)));
        test.setPhone(cursor.getString(cursor.getColumnIndex(Test.PHONE)));
        return test;
    }
}
