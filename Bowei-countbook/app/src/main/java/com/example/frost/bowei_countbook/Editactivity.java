package com.example.frost.bowei_countbook;

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

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Editactivity extends AppCompatActivity {
//this class is designed to work the surface edit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        String message = "Which list you would like to Edit?(Please enter the name of List)";
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(message);


        Button bt_Demo = (Button)findViewById(R.id.button5);
        bt_Demo.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                //This part will handle the "add" function. It will check whether it gives a negative integer or
                //something cannot be converted to integer
                int duration = Toast.LENGTH_SHORT;

                EditText editText = (EditText) findViewById(R.id.editText2);
                String message = editText.getText().toString();
                Context context = getApplicationContext();
                EditText editText2 = (EditText) findViewById(R.id.editText3);
                String initial = editText2.getText().toString();
                //raise exception when input is not integer
                try{
                    int trynum= Integer.parseInt(initial);
                } catch (Exception e){

                    Toast toast = Toast.makeText(context, "initial value is not integer", duration);
                    toast.show();

                    return;
                }
                int init = Integer.parseInt(initial);

                if (Math.signum(init) == -1.0) {
                    Toast toast = Toast.makeText(context, "initial value is negative", duration);
                    toast.show();

                    return;
                    //if negative, go back
                }




                //check whether this item is already in the list
                SharedPreferences sharedPref = context.getSharedPreferences(
                        message, Context.MODE_PRIVATE);
                String cdate = sharedPref.getString("date",null);
                if(cdate != null){

                    Toast toast = Toast.makeText(context, "this item is already in the list", duration);
                    toast.show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPref.edit();

                EditText editText3 = (EditText) findViewById(R.id.editText7);
                String comment = editText3.getText().toString();
                //access the list that saved all the name of the items
                SharedPreferences prefs=getSharedPreferences("The__lists",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=prefs.edit();
                String csvList = prefs.getString("myList","");
                csvList +=message;
                csvList +=",";
                edit.putString("myList", csvList);


                edit.commit();
                //once the item passed test. creat a new name with its name
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                editor.putString("date",  dateFormat.format(date));
                editor.putString("comment",  comment);

                editor.putInt("initial value", init);
                editor.putInt("current value", init);

                editor.commit();

                Toast toast = Toast.makeText(context, "Add success", duration);
                toast.show();


                //响应Clicked事件
                //......
            }
        });
    }
    //this method will handle when user would like to edit an exist item
    public void Handleedit(View view) {
        int duration = Toast.LENGTH_SHORT;

        Intent intent = new Intent(this, ValueActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                message, Context.MODE_PRIVATE);
        String cdate = sharedPref.getString("date",null);
        //check whether this item is exist by checking its date
        if(cdate == null){

            Toast toast = Toast.makeText(context, "Item is not found", duration);
            toast.show();
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.clear();
            editor.commit();
            return;
        }


        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}

