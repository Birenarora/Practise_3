package com.example.practise_3;

import android.util.Log;

public class Product {
    private String _notes;


    public Product() {

    }

    public Product(String notes) {
        this._notes = notes;
        Log.v("MyTag1", "MyValue=" + this._notes);
    }





    public void setNotes(String notes) {
        this._notes = notes;
    }



    public String getNotes() {
        return this._notes;
    }

}

