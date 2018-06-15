package com.chrissetiana.quakereport;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    private static final String REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private static final int LOADER_ID = 1;
    private EarthquakeAdapter adapter;
    TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        ListView earthquakeList = findViewById(R.id.earthquake_list);
        earthquakeList.setAdapter(adapter);

        earthquakeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Earthquake current = adapter.getItem(position);

                Uri earthquakeUri = Uri.parse(current.getUrl());

                Intent webIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(webIntent);
            }
        });

        emptyList = findViewById(R.id.empty_list);
        earthquakeList.setEmptyView(emptyList);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        adapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {
           adapter.addAll(earthquakes);
        }

        emptyList.setText(R.string.empty_list);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        adapter.clear();
    }
}
