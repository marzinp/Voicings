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

    fun populate() {
        val voicing = Voicing()
        var i = 0
        val listoflists = listOf<List<String>>(
            mutableListOf("Maj7", "M7", "Closed", "1", "3 5 M7"),
            mutableListOf("Min7", "11", "Spread", "", ""),
            mutableListOf("Dom7", "5", "Spread", "", ""),
            mutableListOf("Min7", "9", "Rootless", "", ""),
            mutableListOf("Maj7", "13", "Quartal", "1 4 M7", "9 13"),
            mutableListOf("Min7", "b3", "Spread", "", ""),
            mutableListOf("Dom7", "#9", "Spread", "", ""),
            mutableListOf("Dom7", "9", "Rootless", "", ""),
            mutableListOf("Min7b5", "11", "Spread", "", ""),
            mutableListOf("Maj7", "M7", "Spread", "1 3", "#11 6 M7"),
            mutableListOf("Min7", "9", "Spread", "", ""),
            mutableListOf("Dom7", "b13", "Spread", "", ""),
            mutableListOf("Min7", "5", "Closed", "", ""),
            mutableListOf("Maj7", "13", "Spread", "1 3 5", "M7 9 13"),
            mutableListOf("Min7", "7", "Spread", "", ""),
            mutableListOf("Alt", "b9", "Spread", "", ""),
            mutableListOf("Dom7", "11", "Rootless", "", ""),
            mutableListOf("Dim7", "b3", "Closed", "1", "6 b3 b5"),
            mutableListOf("Maj7", "1", "Opened", "3 5 6", "M7 9 1"),
            mutableListOf("Min7", "5", "Spread", "", ""),
            mutableListOf("Dom7", "9", "Spread", "", "")
        )
        while (i < listoflists.size) {
            voicing.voicingId = i
            voicing.type = listoflists[i][0]
            voicing.melody = listoflists[i][1]
            voicing.style = listoflists[i][2]
            voicing.LH = listoflists[i][3]
            voicing.RH = listoflists[i][4]
            i++
            insert(voicing)
        }
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
