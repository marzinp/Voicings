package com.example.Voicings

import android.database.sqlite.SQLiteDatabase


/**
 * Created by Tan on 1/26/2016.
 */
class DatabaseManager {
    private var mOpenCounter = 0
    private var mDatabase: SQLiteDatabase? = null

    @Synchronized
    fun openDatabase(): SQLiteDatabase? {
        mOpenCounter += 1
        if (mOpenCounter == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper!!.writableDatabase
        }
        return mDatabase
    }

    @Synchronized
    fun closeDatabase() {
        mOpenCounter -= 1
        if (mOpenCounter == 0) {
            // Closing database
            mDatabase!!.close()
        }
    }

    companion object {
        private var instance: DatabaseManager? = null
        private var mDatabaseHelper: DBHelper? = null

        @JvmStatic
        @Synchronized
        fun initializeInstance(helper: DBHelper?) {
            if (instance == null) {
                instance = DatabaseManager()
                mDatabaseHelper = helper
            }
        }

        @JvmStatic
        @Synchronized
        fun getInstance(): DatabaseManager? {
            checkNotNull(instance) {
                DatabaseManager::class.java.simpleName +
                        " is not initialized, call initializeInstance(..) method first."
            }

            return instance
        }
    }
}