package com.example.jeevan.testapp.dao;

/**
 * Created by jeevan on 5/20/17.
 */

public class Test1Emp {
    public static final String TABLE_NAME = "EMP";
    public static final String ROWID = "ROWID";
    public static final String NAME = "NAME";
    public static final String DESIGNATION = "DESIGNATION";
    public static final String PHONE = "PHONE";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT UNIQUE NOT NULL, " +
            DESIGNATION + " TEXT, " +
            PHONE + " INTEGER" +
            ")";
    public static final String DELETE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
}

