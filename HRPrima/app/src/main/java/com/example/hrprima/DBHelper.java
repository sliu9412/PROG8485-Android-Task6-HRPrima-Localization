package com.example.hrprima;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // fields
    static String DBNAME = "Company.db";
    static int VERSION = 1;
    static String TABLE_NAME = "Employee";
    static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    static String COL1 = "id";
    static String COL2 = "name";
    static String COL3 = "destination";
    static String COL4 = "department";
    static String COL5 = "emailid";
    static String COL6 = "salary";
    // SQL for creating table
    static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, "
            + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " INTEGER); ";

    // database is create in constructor
    public DBHelper(Context context) {
        // the third param represent cursor
        // if the data is existed, skip creation process, or create a new one
        super(context, DBNAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean insertEmployee(Employee empObj) {
        SQLiteDatabase db = getWritableDatabase(); // get currently opened database
        ContentValues cv = new ContentValues(); // name-value pair (column-value pair)
        cv.put(COL2, empObj.getName());
        cv.put(COL3, empObj.getDesig());
        cv.put(COL4, empObj.getDept());
        cv.put(COL5, empObj.getEmailid());
        cv.put(COL6, empObj.getSalary());
        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    public Cursor readEmployees() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorObj;
        cursorObj = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursorObj != null) {
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }

    public Integer DeleteEmployee(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, "id=" + id, null);
    }

}
