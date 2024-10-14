package com.example.Voicings

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.json.JSONException
import org.json.JSONObject

class DatabaseHandler(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private var CREATE_VOICING_TABLE: String = ("CREATE TABLE " + TABLE_VOICINGS + "(" +
            KEY_VoicingId + " INTEGER PRIMARY KEY," + KEY_VoicingType + " TEXT," + KEY_VoicingMelody + " TEXT,"
            + KEY_VoicingStyle + " TEXT," + KEY_VoicingLH + " TEXT," + KEY_VoicingRH + " TEXT" + ")")

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_VOICING_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOICINGS)
        onCreate(db)
    }

    @Throws(JSONException::class)
    fun addJson(json: JSONObject) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_VoicingId, json.getString("voicingId"))
        values.put(KEY_VoicingType, json.getString("voicingType"))
        values.put(KEY_VoicingMelody, json.getString("voicingMelody"))
        values.put(KEY_VoicingStyle, json.getString("voicingStyle"))
        values.put(KEY_VoicingLH, json.getString("LH"))
        values.put(KEY_VoicingRH, json.getString("RH"))
        db.insert(TABLE_VOICINGS, null, values)
        db.close()
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Voicings"
        private const val TABLE_VOICINGS = "Voicings"
        private const val KEY_VoicingId = "id"
        private const val KEY_VoicingType = "Type"
        private const val KEY_VoicingMelody = "Melody"
        private const val KEY_VoicingStyle = "Style"
        private const val KEY_VoicingLH = "LH"
        private const val KEY_VoicingRH = "RH"


        private const val KEY_DeviceType = "deviceType"
    }
}
