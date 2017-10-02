
/*
 * Class Name : ListActivity
 *
 * Version: V 1.0
 *
 * Date: Oct 1, 2017
 *
 * Copyright  CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the code behaviour of students.
 */
package com.example.frost.bowei_countbook;

/**
 *Represents a ListActivity
 *@author Bowei Wang
 *@version 1.0
 *@see com.example.frost.bowei_countbook.MainActivity
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

//This class will make a list view
public class ListActivity extends AppCompatActivity {

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mainListView = (ListView) findViewById( R.id.mainListView );
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

//question here, use SharedPreferences to save string set will cause lose information

/*
        SharedPreferences prefs=getSharedPreferences("The__lists",Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("The__lists", null);

        ArrayList<String> itemLists = new ArrayList<String>();
        itemLists.addAll( set );
        */
        SharedPreferences prefs=getSharedPreferences("The__lists",Context.MODE_PRIVATE);

        String csvList = prefs.getString("myList",null);
        //access what saved in the name list if nothing is saved, go back
        /*
        if (csvList == null){

            Toast toast = Toast.makeText(context, "The list is empty", duration);
            toast.show();

        }
        */
        String[] items = csvList.split(",");
        List<String> itemLists = new ArrayList<String>();
        //get the list of saved items, check whether its date is null, if it has nothing in date
        //that means the list is empty,then return
        for(int i=0; i < items.length; i++){

            SharedPreferences preferen=getSharedPreferences(items[i],Context.MODE_PRIVATE);
            String cdate = preferen.getString("date",null);
            if(cdate == null){

                Toast toast = Toast.makeText(context, "The list is empty", duration);
                toast.show();

                return;
            }
            int current = preferen.getInt("current value", 0);
            String date = preferen.getString("date", null);
            String info=items[i] +"; current value: "+current +"  "+date;

            itemLists.add(info);

        }


        /*
        for (final ListIterator<String> i = itemLists.listIterator(); i.hasNext();) {
            final String element = i.next();
            SharedPreferences pref=getSharedPreferences(element,Context.MODE_PRIVATE);
            int current = pref.getInt("current value", 0);
            String cur = Integer.toString(current);
            i.set(element + "   current value:"+cur);
        }*/
        duration = Toast.LENGTH_LONG;
        //list view is created here
        int itemCount = itemLists.size();
        Toast toast = Toast.makeText(context, "We current have "+itemCount+" counters", duration);
        toast.show();
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, itemLists);


        mainListView.setAdapter( listAdapter );
        mainListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {
                        //handle button on click to the list

                        Object o = mainListView.getItemAtPosition(position);
                        String item = o.toString();

                        Intent intent = new Intent(view.getContext(), ListInfo.class);

                        //get the item name
                        if(item.contains(";")){
                            item= item.substring(0, item.indexOf(";"));

                        }
                        //If a element of list is clicked, it will show the detailed information
                        intent.putExtra(EXTRA_MESSAGE, item);
                        startActivity(intent);
                    }
                }
        );

    }

}
