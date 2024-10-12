package com.example.myapplication;

public class Voicing {
    public static final String TAG = com.example.myapplication.Type.class.getSimpleName();
    public static final String TABLE = "Voicings";
    // Labels Table Columns names
    public static final String KEY_VoicingId = "VoicingId";
    public static final String KEY_Type = "Type";
    public static final String KEY_Melody = "Melody";
    public static final String KEY_Style = "Style";

    private String VoicingId;
    private String Type;
    private String Melody;
    private String Style;


    public String getVoicingId() {
        return VoicingId;
    }

    public void setVoicingId(String voicingId) {
        this.VoicingId = voicingId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getMelody() {
        return Melody;
    }

    public void setMelody(String melody) {
        this.Melody = melody;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        this.Style = Style;
    }

}
