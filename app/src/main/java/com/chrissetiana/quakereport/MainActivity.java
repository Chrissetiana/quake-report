package com.chrissetiana.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Earthquake> earthquakes = new ArrayList<>();

        earthquakes.add(new Earthquake("2.5", "Kenya", "08 Feb 2018"));
        earthquakes.add(new Earthquake("1.0", "Ghana", "17 Mar 2018"));
        earthquakes.add(new Earthquake("3.4", "Nigeria", "11 Apr 2018"));
        earthquakes.add(new Earthquake("1.2", "Uganda", "13 Apr 2018"));
        earthquakes.add(new Earthquake("4.1", "Botswana", "13 Apr 2018"));

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        ListView earthquakeList = findViewById(R.id.earthquake_list);
        earthquakeList.setAdapter(earthquakeAdapter);
    }
}
