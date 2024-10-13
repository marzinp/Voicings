package com.example.Voicings;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Voicings";
    private static final String TABLE_VOICINGS = "Voicings";
    private static final String KEY_VoicingId = "id";
    private static final String KEY_VoicingType = "Type";
    private static final String KEY_VoicingMelody = "Melody";
    private static final String KEY_VoicingStyle = "Style";
    private static final String KEY_VoicingLH = "LH";
    private static final String KEY_VoicingRH = "RH";


    private static final String KEY_DeviceType = "deviceType";
    String CREATE_VOICING_TABLE = "CREATE TABLE " + TABLE_VOICINGS + "(" +
            KEY_VoicingId + " INTEGER PRIMARY KEY," + KEY_VoicingType + " TEXT," + KEY_VoicingMelody + " TEXT,"
            + KEY_VoicingStyle + " TEXT," + KEY_VoicingLH + " TEXT," + KEY_VoicingRH + " TEXT" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VOICING_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOICINGS);
        onCreate(db);
    }

    void addJson(JSONObject json) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VoicingId, json.getString("voicingId"));
        values.put(KEY_VoicingType, json.getString("voicingType"));
        values.put(KEY_VoicingMelody, json.getString("voicingMelody"));
        values.put(KEY_VoicingStyle, json.getString("voicingStyle"));
        values.put(KEY_VoicingLH, json.getString("voicingLH"));
        values.put(KEY_VoicingRH, json.getString("voicingRH"));
        db.insert(TABLE_VOICINGS, null, values);
        db.close();
    }

}
