package com.example.Voicings;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class VoicingRepo {
    private static Voicing voicing;

    public VoicingRepo() {

        voicing = new Voicing();

    }


    public static String createTable() {
        return "CREATE TABLE " + Voicing.TABLE + " ("
                + Voicing.KEY_VoicingId + "  INT PRIMARY KEY    ,"
                + Voicing.KEY_Type + " TEXT ,"
                + Voicing.KEY_Melody + " TEXT ,"
                + Voicing.KEY_Style + " TEXT )";


    }


    public int insert(Voicing voicing) {
        int voicingId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Voicing.KEY_VoicingId, voicing.getVoicingId());
        values.put(Voicing.KEY_Type, voicing.getType());
        values.put(Voicing.KEY_Melody, voicing.getMelody());
        values.put(Voicing.KEY_Style, voicing.getStyle());


        // Inserting Row
        voicingId = (int) db.insert(Voicing.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return voicingId;
    }


    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Voicing.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

}
