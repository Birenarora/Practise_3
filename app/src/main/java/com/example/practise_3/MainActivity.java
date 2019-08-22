package com.example.practise_3;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText note;
    Button add_note;
    Button delete_note;
    TextView count;
    ListView note_list;
    ArrayList<String> theList;
    ArrayAdapter adapter;
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        note = (EditText)findViewById(R.id.note);
        add_note = (Button)findViewById(R.id.note_add);
        delete_note = (Button)findViewById(R.id.delete_note);
        count = (TextView)findViewById(R.id.count);
        note_list = (ListView)findViewById(R.id.note_list);

        int profile_counts = dbHandler.getProfilesCount();
        count.setText(String.valueOf(profile_counts));
//        viewData();


    }

    public void newProduct (View view) {
//        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String notes = note.getText().toString();
        if(notes.isEmpty()) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();

        }
        else{
            Log.v("MyTag", "MyValue=" + notes);
            Product product =
                    new Product(notes);

            dbHandler.addProduct(product);
            note.setText("");
            int profile_counts = dbHandler.getProfilesCount();
            count.setText(String.valueOf(profile_counts));
            theList = new ArrayList<>();
            viewData();
        }
    }

    private void viewData() {
        Cursor cur = dbHandler.getListContents();
        if(cur.getCount() == 0){
            note_list.setAdapter(null);
        }
        else{
            while (cur.moveToNext()){
                theList.add(cur.getString(0));
            }
            adapter = new ArrayAdapter<>(this, R.layout.activity_listview, theList);
            note_list.setAdapter(adapter);

        }
    }


    public void removeProduct (View view) {
//        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        dbHandler.deleteProduct();
        int profile_counts = dbHandler.getProfilesCount();
        count.setText(String.valueOf(profile_counts));
        viewData();

    }



}