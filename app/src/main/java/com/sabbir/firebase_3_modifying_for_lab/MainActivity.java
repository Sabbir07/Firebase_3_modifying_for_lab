package com.sabbir.firebase_3_modifying_for_lab;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.delay;

/**
 * Created by Sabbir on 015  15 05 17  May.
 */


//Takes firebase link and gets data into app, and adds new data from the app
//users can't modify or delete data from app, only insert and see...
public class MainActivity extends AppCompatActivity {

    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);
        //if not given final it won't work inside setOnClickListener
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);


        //
        // permission
        //
        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this
                                                , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(MainActivity.this
                                                            , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                                                            , EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(MainActivity.this
                                                    , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                                                    , EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }


            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,true);
            editor.commit();

        } /*else {
            //You already have the permission, just go ahead.
            proceedAfterPermission();
        }*/



        final Button all = (Button) findViewById(R.id.all);
        //all.startAnimation(shake);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForAllStudents.class);
                view.startAnimation(shake);
                startActivity(i);
            }
        });


        Button first = (Button) findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForFirstYear.class);
                view.startAnimation(shake);
                startActivity(i);
            }
        });


        Button second = (Button) findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForSecondYear.class);
                view.startAnimation(shake);
                startActivity(i);
            }
        });


        Button third = (Button) findViewById(R.id.third);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForThirdYear.class);
                view.startAnimation(shake);
                startActivity(i);
            }
        });


        Button fourth = (Button) findViewById(R.id.fourth);
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForFourthYear.class);
                view.startAnimation(shake);
                startActivity(i);
            }
        });


        Button masters = (Button) findViewById(R.id.masters);
        masters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForMasters.class);
                view.startAnimation(shake);
                startActivity(i);
            }
        });
    }
}
