package com.chrissetiana.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("Kenya");
        earthquakes.add("Ghana");
        earthquakes.add("Nigeria");
        earthquakes.add("Uganda");
        earthquakes.add("Botswana");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, earthquakes);

        ListView earthquakeList = findViewById(R.id.earthquake_list);
        earthquakeList.setAdapter(adapter);
    }
}
