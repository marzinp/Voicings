package com.example.Voicings

import android.content.ContentValues
import com.example.Voicings.DatabaseManager.Companion.getInstance

class StyleRepo {
    init {
        val style = Style()
    }


    fun insert(Style: Style): Int {
        val styleId: Int
        val db = getInstance()!!.openDatabase()
        val values = ContentValues()
        values.put(com.example.Voicings.Style.KEY_StyleId, Style.styleId)
        values.put(com.example.Voicings.Style.KEY_Name, Style.name)

        // Inserting Row
        styleId = db!!.insert(com.example.Voicings.Style.TABLE, null, values).toInt()
        getInstance()!!.closeDatabase()

        return styleId
    }


    fun delete() {
        val db = getInstance()!!.openDatabase()
        db!!.delete(Style.Companion.TABLE, null, null)
        getInstance()!!.closeDatabase()
    }

    companion object {
        fun createTable(): String {
            return ("CREATE TABLE " + Style.Companion.TABLE + "("
                    + Style.Companion.KEY_StyleId + "  INTEGER PRIMARY KEY   ,"
                    + Style.Companion.KEY_Name + " TEXT )")
        }
    }
}
