package com.example.Voicings

class Melody {
    var melodyId: String? = null

    @JvmField
    var name: String? = null


    companion object {
        //public static final String TAG = com.example.Voicings.Melody.class.getSimpleName();
        const val TABLE: String = "Melodys"

        // Labels Table Columns names
        const val KEY_MelodyId: String = "MelodyId"
        const val KEY_Name: String = "Name"
    }
}
