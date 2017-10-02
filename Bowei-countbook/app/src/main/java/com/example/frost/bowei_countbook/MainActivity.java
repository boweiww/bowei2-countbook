

/*
 * Class Name : MainActivity
 *
 * Version: V 1.0
 *
 * Date: Oct 1, 2017
 *
 * Copyright  CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the code behaviour of students.
 */
package com.example.frost.bowei_countbook;

/**
 *Represents a MainActivity
 *@author Bowei Wang
 *@version 1.0
 *@see android.app.Activity********************************
 */
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //This main activity class is created as the surface, let the user select whether edit or list
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    // on click is not used her because button menu provide the function to connect, but not provided in other classes
    //would ask question here
    public void ButtonList (View v) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void ButtonEdit (View v) {
        Intent intent = new Intent(this, Editactivity.class);
        startActivity(intent);
    }

}
