package com.example.Voicings;


import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tan on 1/26/2016.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private static DBHelper mDatabaseHelper;
    private Integer mOpenCounter = 0;
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(DBHelper helper) {
        if (instance == null) {
            instance = new DatabaseManager();
            mDatabaseHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return instance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        mOpenCounter += 1;
        if (mOpenCounter == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        mOpenCounter -= 1;
        if (mOpenCounter == 0) {
            // Closing database
            mDatabase.close();

        }
    }
}