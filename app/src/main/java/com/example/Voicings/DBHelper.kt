package com.example.Voicings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 8;
    // Database Name
    private static final String DATABASE_NAME = "voicings.db";
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "createTable");
        //All necessary tables you like to create will create here
        db.execSQL(TypeRepo.createTable());
        db.execSQL(MelodyRepo.createTable());
        db.execSQL(StyleRepo.createTable());
        db.execSQL(VoicingRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Type.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Melody.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Style.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Voicing.TABLE);
        onCreate(db);
    }

}