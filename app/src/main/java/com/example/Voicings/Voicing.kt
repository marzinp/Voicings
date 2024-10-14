package com.example.Voicings

class Voicing {
    var voicingId: Int = 0
    var type: String = ""
    var melody: String = ""
    var style: String = ""
    var LH: String = ""
    var RH: String = ""
    companion object {
        //public static final String TAG = com.example.Voicings.Type.class.getSimpleName();
        const val TABLE: String = "Voicings"

        // Labels Table Columns names
        var KEY_VoicingId: String = "VoicingId"
        var KEY_Type: String = "Type"
        var KEY_Melody: String = "Melody"
        var KEY_Style: String = "Style"
        var KEY_LH: String = "LH"
        var KEY_RH: String = "RH"
    }
}
