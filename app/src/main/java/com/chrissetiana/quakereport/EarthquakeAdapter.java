package com.chrissetiana.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_layout, parent, false);
        }

        Earthquake current = getItem(position);

        TextView magnitude = listItemView.findViewById(R.id.earthquake_magnitude);
        magnitude.setText(current.getMagnitude());

        TextView city = listItemView.findViewById(R.id.earthquake_location);
        city.setText(current.getLocation());

        TextView date = listItemView.findViewById(R.id.earthquake_date);
        date.setText(current.getDate());

        return listItemView;
    }
}
