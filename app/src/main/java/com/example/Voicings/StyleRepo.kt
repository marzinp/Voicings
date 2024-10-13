package com.example.Voicings;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class StyleRepo {

    public StyleRepo() {

        com.example.Voicings.Style style = new Style();

    }


    public static String createTable() {
        return "CREATE TABLE " + com.example.Voicings.Style.TABLE + "("
                + com.example.Voicings.Style.KEY_StyleId + "  INTEGER PRIMARY KEY   ,"
                + com.example.Voicings.Style.KEY_Name + " TEXT )";
    }


    public int insert(Style Style) {
        int styleId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(com.example.Voicings.Style.KEY_StyleId, Style.getStyleId());
        values.put(com.example.Voicings.Style.KEY_Name, Style.getName());

        // Inserting Row
        styleId = (int) db.insert(com.example.Voicings.Style.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return styleId;
    }


    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(com.example.Voicings.Style.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

}
