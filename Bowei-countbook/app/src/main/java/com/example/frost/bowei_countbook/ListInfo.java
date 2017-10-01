package com.example.frost.bowei_countbook;

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
        String message = intent.getStringExtra(android.provider.AlarmClock.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                message, Context.MODE_PRIVATE);
        int current = sharedPref.getInt("current value", 0);
        String cur = Integer.toString(current);
        TextView textView1 = (TextView) findViewById(R.id.textView13);
        textView1.setText("current value: "+cur);
        int initial = sharedPref.getInt("initial value", 0);
        String init = Integer.toString(initial);
        TextView textView2 = (TextView) findViewById(R.id.textView12);
        textView2.setText("initial value: "+init);
        String date = sharedPref.getString("date", null);
        TextView textView3 = (TextView) findViewById(R.id.textView15);
        textView3.setText("last edit: "+date);
        String comment = sharedPref.getString("comment", null);
        TextView textView4 = (TextView) findViewById(R.id.textView14);
        textView4.setText("comment: "+comment);

    }
}
