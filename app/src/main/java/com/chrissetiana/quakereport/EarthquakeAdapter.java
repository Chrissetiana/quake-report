package com.chrissetiana.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        Date dateTime = new Date(current.getTime());

        TextView date = listItemView.findViewById(R.id.earthquake_date);
        String formattedDate = formatDate(dateTime);
        date.setText(formattedDate);

        TextView time = listItemView.findViewById(R.id.earthquake_time);
        String formattedTime = formatTime(dateTime);
        time.setText(formattedTime);

        return listItemView;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("MMM dd, yyyy").format(date);
    }

    private String formatTime(Date time) {
        return new SimpleDateFormat("HH:mm:ss").format(time);
    }
}
