package com.example.Voicings

class Type {
    var typeId: String? = null
    var name: String? = null


    companion object {
        //public static final String TAG = Type.class.getSimpleName();
        const val TABLE: String = "Types"

        // Labels Table Columns names
        const val KEY_TypeId: String = "TypeId"
        const val KEY_Name: String = "Name"
    }
}
