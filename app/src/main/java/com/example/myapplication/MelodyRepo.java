package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MelodyRepo {
    private static Melody melody;

    public MelodyRepo() {

        melody = new Melody();

    }


    public static String createTable() {
        return "CREATE TABLE " + Melody.TABLE + "("
                + Melody.KEY_MelodyId + "   PRIMARY KEY    ,"
                + Melody.KEY_Name + " TEXT )";
    }


    public int insert(Melody melody) {
        int melodyId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Melody.KEY_MelodyId, melody.getMelodyId());
        values.put(Melody.KEY_Name, melody.getName());

        // Inserting Row
        melodyId = (int) db.insert(Melody.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return melodyId;
    }


    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Melody.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

}
