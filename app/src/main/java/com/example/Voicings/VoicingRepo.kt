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
        values.put(Voicing.KEY_VoicingId, voicing.voicingId)
        values.put(Voicing.KEY_Type, voicing.type)
        values.put(Voicing.KEY_Melody, voicing.melody)
        values.put(Voicing.KEY_Style, voicing.style)
        values.put(Voicing.KEY_LH, voicing.LH)
        values.put(Voicing.KEY_RH, voicing.RH)


        // Inserting Row
        voicingId = db!!.insert(Voicing.TABLE, null, values).toInt()
        getInstance()!!.closeDatabase()

        return voicingId
    }


    fun delete() {
        val db = getInstance()!!.openDatabase()
        db!!.delete(Voicing.TABLE, null, null)
        getInstance()!!.closeDatabase()
    }

    companion object {
        private lateinit var voicing: Voicing

        fun createTable(): String {
            return ("CREATE TABLE " + Voicing.TABLE + " ("
                    + Voicing.KEY_VoicingId + "  INT PRIMARY KEY    ,"
                    + Voicing.KEY_Type + " TEXT ,"
                    + Voicing.KEY_Melody + " TEXT ,"
                    + Voicing.KEY_Style + " TEXT ,"
                    + Voicing.KEY_LH + " TEXT ,"
                    + Voicing.KEY_RH + " TEXT )")
        }
    }
}
