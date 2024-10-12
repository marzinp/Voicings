package com.example.myapplication;

public class Type {

    public static final String TAG = Type.class.getSimpleName();
    public static final String TABLE = "Types";
    // Labels Table Columns names
    public static final String KEY_TypeId = "TypeId";
    public static final String KEY_Name = "Name";

    private String TypeId;
    private String name;


    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String courseId) {
        this.TypeId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
