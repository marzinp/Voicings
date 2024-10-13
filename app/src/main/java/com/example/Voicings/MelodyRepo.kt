package com.example.Voicings

import android.content.ContentValues
import com.example.Voicings.DatabaseManager.Companion.getInstance

class MelodyRepo {
    init {
        melody = Melody()
    }


    fun insert(melody: Melody): Int {
        val melodyId: Int
        val db = getInstance()!!.openDatabase()
        val values = ContentValues()
        values.put(Melody.KEY_MelodyId, melody.melodyId)
        values.put(Melody.KEY_Name, melody.name)

        // Inserting Row
        melodyId = db!!.insert(Melody.TABLE, null, values).toInt()
        getInstance()!!.closeDatabase()

        return melodyId
    }


    fun delete() {
        val db = getInstance()!!.openDatabase()
        db!!.delete(Melody.TABLE, null, null)
        getInstance()!!.closeDatabase()
    }

    companion object {
        private lateinit var melody: Melody

        fun createTable(): String {
            return ("CREATE TABLE " + Melody.TABLE + "("
                    + Melody.KEY_MelodyId + "  INTEGER PRIMARY KEY    ,"
                    + Melody.KEY_Name + " TEXT )")
        }
    }
}
