package com.example.Voicings;

public class Melody {
    //public static final String TAG = com.example.Voicings.Melody.class.getSimpleName();
    public static final String TABLE = "Melodys";
    // Labels Table Columns names
    public static final String KEY_MelodyId = "MelodyId";
    public static final String KEY_Name = "Name";

    private String MelodyId;
    private String name;


    public String getMelodyId() {
        return MelodyId;
    }

    public void setMelodyId(String melodyId) {
        this.MelodyId = melodyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
