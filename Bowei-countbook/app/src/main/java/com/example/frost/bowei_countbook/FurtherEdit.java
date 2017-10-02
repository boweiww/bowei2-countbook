
/*
 * Class Name : FurtherEdit
 *
 * Version: V 1.0
 *
 * Date: Oct 1, 2017
 *
 * Copyright  CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the code behaviour of students.
 */
package com.example.frost.bowei_countbook;

/**
 *Represents a FurtherEdit
 *@author Bowei Wang
 *@version 1.0
 *@see com.example.frost.bowei_countbook.ValueActivity
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//This class is designed to directly edit the item
//all the edit will change the date
public class FurtherEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_further_edit);
        Intent intent = getIntent();

        String message = intent.getStringExtra(android.provider.AlarmClock.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView8);
        //This part does the textview job, it will show the current value and initial value
        textView.setText("Editing: "+message);
        final Context context = getApplicationContext();
        final  int duration = Toast.LENGTH_SHORT;

        final SharedPreferences sharedPref = context.getSharedPreferences(
                message, Context.MODE_PRIVATE);
        int current = sharedPref.getInt("current value", 0);
        int initial = sharedPref.getInt("initial value", 0);
        //String comment = sharedPref.getString("comment",null);
        TextView textView1 = (TextView) findViewById(R.id.textView10);
        textView1.setText("current value: " + current);
        TextView textView2 = (TextView) findViewById(R.id.textView11);
        textView2.setText("initial value: " + initial);

        Button bt_scv = (Button) findViewById(R.id.button11);
        bt_scv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when you decide to edit current value, this function will check whether it is valid
                EditText editText = (EditText) findViewById(R.id.editText5);
                String cur = editText.getText().toString();
                //check whether input is an integer, if not, raise exception
                try{
                    int trynum =Integer.parseInt(cur);
                } catch ( Exception e){

                    Toast toast = Toast.makeText(context, "Please enter an integer", duration);
                    toast.show();

                    return;
                }
                int current = Integer.parseInt(cur);
                //check whether input is negative
                if (Math.signum(current) == -1.0) {
                    Toast toast = Toast.makeText(context, "current value is negative", duration);
                    toast.show();

                    return;
                    //if negative, go back
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("current value", current);
                //save new current value and edit date
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                editor.putString("date",  dateFormat.format(date));
                editor.commit();
                TextView textView1 = (TextView) findViewById(R.id.textView10);
                textView1.setText("current value: " + current);
            }
        });

        Button bt_siv = (Button) findViewById(R.id.button8);
        bt_siv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when you decide to edit initial value, this function will check whether it is valid

                EditText editText = (EditText) findViewById(R.id.editText4);
                String ini = editText.getText().toString();
                //check whether input is an integer, if not, raise exception

                try{
                    int trynum1 =Integer.parseInt(ini);
                } catch ( Exception e){

                    Toast toast = Toast.makeText(context, "Please enter an integer", duration);
                    toast.show();

                    return;
                }
                int init = Integer.parseInt(ini);
                //check whether input is negative

                if (Math.signum(init) == -1.0) {
                    Toast toast = Toast.makeText(context, "initial value is negative", duration);
                    toast.show();

                    return;
                    //if negative, go back
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("initial value", init);
                //save new initial value and edit date

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                editor.putString("date",  dateFormat.format(date));
                editor.commit();
                TextView textView1 = (TextView) findViewById(R.id.textView11);
                textView1.setText("initial value: " + init);
            }
        });

        Button bt_scomment = (Button) findViewById(R.id.button12);
        bt_scomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This method handle comment edit
                EditText editText = (EditText) findViewById(R.id.editText6);
                String comment = editText.getText().toString();

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("comment", comment);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                editor.putString("date",  dateFormat.format(date));
                editor.commit();
            }

        });


        Button bt_final = (Button) findViewById(R.id.button13);
        bt_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This method connect finish button and will go back to main activity

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
