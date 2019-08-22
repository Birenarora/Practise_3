package com.example.practise_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notesDB.db";
    private static final String TABLE_NOTES = "note_store";


    public static final String COLUMN_NOTES = "notes";


    public MyDBHandler(Context context, String name, CursorFactory factory,
                       int version) {
        super(context,DATABASE_NAME , factory, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String CREATE_NOTES_TABLE = "CREATE TABLE " +
                TABLE_NOTES + "("+ COLUMN_NOTES + " TEXT" + ")";
        db.execSQL(CREATE_NOTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
    public void addProduct(Product product) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTES, product.getNotes());


        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NOTES, null, values);
        db.close();
    }
    public Product findProduct(String productname) {
        String query = "Select * FROM " + TABLE_NOTES + " WHERE " + COLUMN_NOTES + " =  \"" + productname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product product = new Product();

        if (cursor.moveToFirst()) {
            //cursor.moveToFirst();
            product.setNotes(cursor.getString(0));
            cursor.close();
        } else {
            product = null;
        }
        db.close();
        return product;
    }
    public void deleteProduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NOTES + "";

        db.execSQL(query);
        db.close();
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NOTES,null);
        return data;
    }
}
