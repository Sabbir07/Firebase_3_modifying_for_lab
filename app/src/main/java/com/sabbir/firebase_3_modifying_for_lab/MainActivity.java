package com.sabbir.firebase_3_modifying_for_lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sabbir on 015  15 05 17  May.
 */


//Takes firebase link and gets data into app, and adds new data from the app
//users can't modify or delete data from app, only insert and see...
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        Button all = (Button) findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForAllStudents.class);
                startActivity(i);
            }
        });


        Button first = (Button) findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForFirstYear.class);
                startActivity(i);
            }
        });


        Button second = (Button) findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForSecondYear.class);
                startActivity(i);
            }
        });


        Button third = (Button) findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForThirdYear.class);
                startActivity(i);
            }
        });


        Button fourth = (Button) findViewById(R.id.fourth);
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForFourthYear.class);
                startActivity(i);
            }
        });


        Button masters = (Button) findViewById(R.id.masters);
        masters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForMasters.class);
                startActivity(i);
            }
        });
    }
}
