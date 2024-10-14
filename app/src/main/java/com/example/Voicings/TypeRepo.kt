package com.example.Voicings

import android.content.ContentValues
import com.example.Voicings.DatabaseManager.Companion.getInstance

/**
 * Created by Tan on 1/26/2016.
 */
class TypeRepo {
    init {
        type = Type()
    }


    fun insert(type: Type): Int {
        val typeId: Int
        val db = getInstance()!!.openDatabase()
        val values = ContentValues()
        values.put(Type.KEY_TypeId, type.typeId)
        values.put(Type.KEY_Name, type.name)

        // Inserting Row
        typeId = db!!.insert(Type.TABLE, null, values).toInt()
        getInstance()!!.closeDatabase()

        return typeId
    }

    fun populate() {
        val db = getInstance()!!.openDatabase()
        delete()
        //Insert Sample data if the table is empty
        val type = Type()
        var i = 0
        var list: List<String> = ArrayList(listOf("Maj7", "Min7", "Dom7", "Min7b5", "Dim7", "Alt"))
        while (i < list.size) {
            type.name = list[i]
            type.typeId = i.toString()
            i++
            insert(type)
        }
    }

    fun delete() {
        val db = getInstance()!!.openDatabase()
        db!!.delete(Type.TABLE, null, null)
        getInstance()!!.closeDatabase()
    }

    companion object {
        private lateinit var type: Type

        fun createTable(): String {
            return ("CREATE TABLE " + Type.TABLE + "("
                    + Type.KEY_TypeId + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL   ,"
                    + Type.KEY_Name + " TEXT )")
        }
    }
}
