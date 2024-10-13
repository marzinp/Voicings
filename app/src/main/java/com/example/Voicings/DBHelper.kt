package com.example.Voicings

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        Log.v(TAG, "createTable")
        //All necessary tables you like to create will create here
        db.execSQL(TypeRepo.createTable())
        db.execSQL(MelodyRepo.createTable())
        db.execSQL(StyleRepo.createTable())
        db.execSQL(VoicingRepo.createTable())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion))

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Type.TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + Melody.TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + Style.TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + Voicing.TABLE)
        onCreate(db)
    }

    companion object {
        //version number to upgrade database version
        //each time if you Add, Edit table, you need to change the
        //version number.
        private const val DATABASE_VERSION = 8

        // Database Name
        private const val DATABASE_NAME = "voicings.db"
        private val TAG: String = DBHelper::class.java.simpleName
    }
}