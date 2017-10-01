package com.example.frost.bowei_countbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static android.R.id.list;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by frost on 2017-09-27.
 */

public class ValueActivity extends AppCompatActivity {
    private String message;
//This class is designed to handle simple increase/decrease/reset
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_value);
        final  int duration = Toast.LENGTH_SHORT;

        Intent intent = getIntent();
        message = intent.getStringExtra(android.provider.AlarmClock.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView5);
        String message2 = "we are handling "+message;
        textView.setText(message2);
        final Context context = getApplicationContext();

        final SharedPreferences sharedPref = context.getSharedPreferences(
                message, Context.MODE_PRIVATE);
        int current = sharedPref.getInt("current value", 0);
        TextView textView2 = (TextView) findViewById(R.id.textView7);
        String info = "current value: " + current;

        textView2.setText(info);

        Button bt_Dec = (Button)findViewById(R.id.button7);
        bt_Dec.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //This method is to decrease value
                int current = sharedPref.getInt("current value", 0);
                current = current -1;

                if (Math.signum(current) == -1.0) {
                    Toast toast = Toast.makeText(context, "current value is negative", duration);
                    toast.show();

                    return;
                    //if negative, go back
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("current value", current);

                TextView textView2 = (TextView) findViewById(R.id.textView7);
                String info = "current value: " + current;

                textView2.setText(info);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                editor.putString("date",  dateFormat.format(date));
                editor.commit();

                //响应Clicked事件
                //......
            }
        });
        Button bt_Inc = (Button)findViewById(R.id.button3);
        bt_Inc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //This method is designed to increase value

                int current = sharedPref.getInt("current value", 0);
                current = current +1;
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("current value", current);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                editor.putString("date",  dateFormat.format(date));
                editor.commit();

                TextView textView2 = (TextView) findViewById(R.id.textView7);
                String info = "current value: " + current;

                textView2.setText(info);

            }
        });
        //reset
        Button bt_Res = (Button)findViewById(R.id.button9);
        bt_Res.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //This method is designed to handle reset
                int initial = sharedPref.getInt("initial value", 0);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("current value", initial);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                editor.putString("date",  dateFormat.format(date));
                editor.commit();

                TextView textView2 = (TextView) findViewById(R.id.textView7);
                String info = "current value: " + initial;

                textView2.setText(info);

            }
        });
        //delete
        Button bt_Del = (Button)findViewById(R.id.button10);
        bt_Del.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //This method is designed to delete current item
                SharedPreferences.Editor editor = sharedPref.edit();

                SharedPreferences prefs=getSharedPreferences("The__lists",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=prefs.edit();
                String csvList = prefs.getString("myList",null);
                String[] items = csvList.split(",");
                List<String> list = new ArrayList<String>();
                for(int i=0; i < items.length; i++){
                    list.add(items[i]);
                }
                list.remove(message);

                StringBuilder csList = new StringBuilder();
                for(String s : list){
                    csList.append(s);
                    csList.append(",");
                }

                edit.putString("myList", csList.toString());
                edit.commit();
                editor.clear();
                editor.commit();
                finish();

                //
                //......
            }
        });


    }

    public void FurtherEdit(View view) {
        //If you would like to directly edit

        Intent intent = new Intent(this, FurtherEdit.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
