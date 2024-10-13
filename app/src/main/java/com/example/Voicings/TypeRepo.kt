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
