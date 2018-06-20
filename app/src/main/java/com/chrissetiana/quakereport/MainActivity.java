package com.chrissetiana.quakereport;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    // private static final String REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private static final String REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    private static final int LOADER_ID = 1;
    TextView emptyText;
    View progressBar;
    private EarthquakeAdapter adapter;

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

        progressBar = findViewById(R.id.progress_bar);
        emptyText = findViewById(R.id.empty_list);
        earthquakeList.setEmptyView(emptyText);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            emptyText.setText(R.string.no_conn);
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String minMag = sharedPref.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));
        Uri uri = Uri.parse(REQUEST_URL);
        Uri.Builder builder = uri.buildUpon();

        builder.appendQueryParameter("format", "geojson");
        builder.appendQueryParameter("limit", "10");
        builder.appendQueryParameter("minmag", minMag);
        builder.appendQueryParameter("orderby", "time");

        return new EarthquakeLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        progressBar.setVisibility(View.GONE);
        emptyText.setText(R.string.no_result);
        adapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {
            adapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        adapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
