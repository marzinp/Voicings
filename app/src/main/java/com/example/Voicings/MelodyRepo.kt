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
    fun populate() {
        val melody = Melody()
        delete()
        var i = 0
        val list = ArrayList(
            listOf(
                "1",
                "2",
                "b3",
                "3",
                "4",
                "b5",
                "5",
                "#5",
                "6",
                "7",
                "M7",
                "b9",
                "9",
                "11",
                "#11",
                "b13",
                "13"
            )
        )
        while (i < list.size) {
            melody.name = list[i]
            melody.melodyId = i.toString()
            i++
            insert(melody)
        }
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
