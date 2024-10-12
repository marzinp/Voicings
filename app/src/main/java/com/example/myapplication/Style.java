package com.example.myapplication;

public class Style {

    public static final String TAG = com.example.myapplication.Type.class.getSimpleName();
    public static final String TABLE = "Styles";
    // Labels Table Columns names
    public static final String KEY_StyleId = "StyleId";
    public static final String KEY_Name = "Name";

    private String StyleId;
    private String name;


    public String getStyleId() {
        return StyleId;
    }

    public void setStyleId(String styleId) {
        this.StyleId = styleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
