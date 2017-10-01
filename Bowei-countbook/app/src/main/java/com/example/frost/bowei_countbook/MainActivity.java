package com.example.frost.bowei_countbook;



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
