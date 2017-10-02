
/*
 * Class Name : ListInfo
 *
 * Version: V 1.0
 *
 * Date: Oct 1, 2017
 *
 * Copyright  CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the code behaviour of students.
 */
package com.example.frost.bowei_countbook;

/**
 *Represents a ListInfo
 *@author Bowei Wang
 *@version 1.0
 *@see com.example.frost.bowei_countbook.ListActivity
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListInfo extends AppCompatActivity {
//This class is designed to print all the information of an item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info);

        Intent intent = getIntent();
        //get message provided by ListActivity
        String message = intent.getStringExtra(android.provider.AlarmClock.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                message, Context.MODE_PRIVATE);
        //Show current value
        int current = sharedPref.getInt("current value", 0);
        String cur = Integer.toString(current);
        TextView textView1 = (TextView) findViewById(R.id.textView13);
        textView1.setText("current value: "+cur);
        //Show initial value
        int initial = sharedPref.getInt("initial value", 0);
        String init = Integer.toString(initial);
        TextView textView2 = (TextView) findViewById(R.id.textView12);
        textView2.setText("initial value: "+init);
        //Show last edit date
        String date = sharedPref.getString("date", null);
        TextView textView3 = (TextView) findViewById(R.id.textView15);
        textView3.setText("last edit: "+date);
        //Show comment
        String comment = sharedPref.getString("comment", null);
        TextView textView4 = (TextView) findViewById(R.id.textView14);
        textView4.setText("comment: "+comment);

    }
}
