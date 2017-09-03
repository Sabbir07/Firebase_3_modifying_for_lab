package com.sabbir.firebase_3_modifying_for_lab;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;


public class ForFirstYear extends AppCompatActivity {

    final static String DB_URL = "https://fir-1-c8ba4.firebaseio.com/";

    EditText nameTxt, descTxt;
    Button saveBtn;
    ArrayList<String> year = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    Firebase fire;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv = (ListView) findViewById(R.id.lv);

        Firebase.setAndroidContext(this);
        fire = new Firebase(DB_URL);


        this.retrieveData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }


    //DISPLAY INPUT DIALOG
    private void showDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Save Outline");
        d.setContentView(R.layout.dialoglayout);

        nameTxt = (EditText) d.findViewById(R.id.nameEditText);
        descTxt = (EditText) d.findViewById(R.id.descEditText);
        saveBtn = (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(nameTxt.getText().toString(), descTxt.getText().toString());

                nameTxt.setText("");
                descTxt.setText("");
            }
        });

        d.show();
    }

    //ADD DATA
    private void addData(String year, String desc) {
        //Movie m= new Movie();
        Movie m = new Movie();
        m.setYear(year);
        m.setDescription(desc);

        fire.child("Movie").push().setValue(m);
    }


    //RETRIEVE
    private void retrieveData() {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    private void getUpdates(DataSnapshot ds) {
        //year.clear();
        descriptions.clear();
        year.clear();

        for (DataSnapshot data : ds.getChildren()) {
            Movie m = new Movie();
            m.setDescription(data.getValue(Movie.class).getDescription());

            Movie m2 = new Movie();
            m2.setYear(data.getValue(Movie.class).getYear());

            //year.add(m2.getYear());
            String a = m2.getYear();

            if (a.equals("First")){
                descriptions.add(m.getDescription());
            }
        }

        if (descriptions.size() > 0) {
            ArrayAdapter adapter = new ArrayAdapter(ForFirstYear.this, android.R.layout.simple_list_item_1, descriptions);
            lv.setAdapter(adapter);
        }else {
            Toast.makeText(ForFirstYear.this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }


}