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


public class ForAllStudents extends AppCompatActivity {

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
        //InformationClass m= new InformationClass();
        InformationClass m = new InformationClass();
        m.setYear(year);
        m.setDescription(desc);

        fire.child("InformationClass").push().setValue(m);
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

        for (DataSnapshot data : ds.getChildren()) {
            InformationClass m = new InformationClass();
            //m.setYear(data.getValue(InformationClass.class).getYear());
            m.setDescription(data.getValue(InformationClass.class).getDescription());

            //year.add(m.getYear());
            descriptions.add(m.getDescription());
        }

        if (descriptions.size() > 0) {
            //ArrayAdapter adapter = new ArrayAdapter(ForAllStudents.this, android.R.layout.simple_list_item_1, year);
            ArrayAdapter adapter = new ArrayAdapter(ForAllStudents.this, android.R.layout.simple_list_item_1, descriptions);
            lv.setAdapter(adapter);
        }else {
            Toast.makeText(ForAllStudents.this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }


}