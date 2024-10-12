package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class StyleRepo {
    private static Style Style;

    public StyleRepo() {

        Style = new Style();

    }


    public static String createTable() {
        return "CREATE TABLE " + com.example.myapplication.Style.TABLE + "("
                + com.example.myapplication.Style.KEY_StyleId + "   PRIMARY KEY    ,"
                + com.example.myapplication.Style.KEY_Name + " TEXT )";
    }


    public int insert(Style Style) {
        int styleId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(com.example.myapplication.Style.KEY_StyleId, Style.getStyleId());
        values.put(com.example.myapplication.Style.KEY_Name, Style.getName());

        // Inserting Row
        styleId = (int) db.insert(com.example.myapplication.Style.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return styleId;
    }


    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(com.example.myapplication.Style.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

}
