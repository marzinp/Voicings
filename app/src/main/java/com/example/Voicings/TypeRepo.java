package com.example.Voicings;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tan on 1/26/2016.
 */
public class TypeRepo {

    private static Type type;

    public TypeRepo() {

        type = new Type();

    }


    public static String createTable() {
        return "CREATE TABLE " + Type.TABLE + "("
                + Type.KEY_TypeId + "  INTEGER PRIMARY KEY   ,"
                + Type.KEY_Name + " TEXT )";
    }


    public int insert(Type type) {
        int typeId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Type.KEY_TypeId, type.getTypeId());
        values.put(Type.KEY_Name, type.getName());

        // Inserting Row
        typeId = (int) db.insert(Type.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return typeId;
    }


    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Type.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
