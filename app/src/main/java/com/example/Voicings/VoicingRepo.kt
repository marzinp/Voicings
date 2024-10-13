package com.example.Voicings

import android.content.ContentValues
import com.example.Voicings.DatabaseManager.Companion.getInstance

class VoicingRepo {
    init {
        voicing = Voicing()
    }


    fun insert(voicing: Voicing): Int {
        val voicingId: Int
        val db = getInstance()!!.openDatabase()
        val values = ContentValues()
        values.put(Voicing.Companion.KEY_VoicingId, voicing.voicingId)
        values.put(Voicing.Companion.KEY_Type, voicing.type)
        values.put(Voicing.Companion.KEY_Melody, voicing.melody)
        values.put(Voicing.Companion.KEY_Style, voicing.style)


        // Inserting Row
        voicingId = db!!.insert(Voicing.Companion.TABLE, null, values).toInt()
        getInstance()!!.closeDatabase()

        return voicingId
    }


    fun delete() {
        val db = getInstance()!!.openDatabase()
        db!!.delete(Voicing.Companion.TABLE, null, null)
        getInstance()!!.closeDatabase()
    }

    companion object {
        private lateinit var voicing: Voicing

        fun createTable(): String {
            return ("CREATE TABLE " + Voicing.Companion.TABLE + " ("
                    + Voicing.Companion.KEY_VoicingId + "  INT PRIMARY KEY    ,"
                    + Voicing.Companion.KEY_Type + " TEXT ,"
                    + Voicing.Companion.KEY_Melody + " TEXT ,"
                    + Voicing.Companion.KEY_Style + " TEXT )")
        }
    }
}
