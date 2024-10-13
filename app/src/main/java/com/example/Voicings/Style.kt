package com.example.Voicings

class Style {
    var styleId: String? = null
    var name: String? = null


    companion object {
        //public static final String TAG = com.example.Voicings.Type.class.getSimpleName();
        const val TABLE: String = "Styles"

        // Labels Table Columns names
        const val KEY_StyleId: String = "StyleId"
        const val KEY_Name: String = "Name"
    }
}
