package com.example.Voicings

class Voicing {
    var voicingId: String? = null
    var type: String? = null
    var melody: String? = null
    var style: String? = null


    companion object {
        //public static final String TAG = com.example.Voicings.Type.class.getSimpleName();
        const val TABLE: String = "Voicings"

        // Labels Table Columns names
        const val KEY_VoicingId: String = "VoicingId"
        const val KEY_Type: String = "Type"
        const val KEY_Melody: String = "Melody"
        const val KEY_Style: String = "Style"
    }
}
