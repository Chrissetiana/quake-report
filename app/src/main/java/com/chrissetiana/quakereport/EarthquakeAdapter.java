package com.chrissetiana.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private final static String SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String loc_offset, loc_primary;

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_layout, parent, false);
        }

        Earthquake current = getItem(position);

        TextView magnitude = listItemView.findViewById(R.id.earthquake_magnitude);
        String formattedMagnitude = formatMagnitude(current.getMagnitude());
        magnitude.setText(formattedMagnitude);

        String location = current.getLocation();
        if (location.contains(SEPARATOR)) {
            String[] loc = location.split(SEPARATOR);
            loc_offset = loc[0] + SEPARATOR;
            loc_primary = loc[1];
        } else {
            loc_offset = "Near the";
            loc_primary = location;
        }

        TextView location_offset = listItemView.findViewById(R.id.earthquake_loc_offset);
        location_offset.setText(loc_offset);

        TextView location_primary = listItemView.findViewById(R.id.earthquake_loc_primary);
        location_primary.setText(loc_primary);

        Date dateTime = new Date(current.getTime());

        TextView date = listItemView.findViewById(R.id.earthquake_date);
        String formattedDate = formatDate(dateTime);
        date.setText(formattedDate);

        TextView time = listItemView.findViewById(R.id.earthquake_time);
        String formattedTime = formatTime(dateTime);
        time.setText(formattedTime);

        GradientDrawable magCircle = (GradientDrawable) magnitude.getBackground();
        int magColor = getMagnitudeColor(current.getMagnitude());
        magCircle.setColor(magColor);

        return listItemView;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("MMM dd, yyyy").format(date);
    }

    private String formatTime(Date time) {
        return new SimpleDateFormat("hh:mm a").format(time);
    }

    private String formatMagnitude(double mag) {
        return new DecimalFormat("0.0").format(mag);
    }

    private int getMagnitudeColor(double mag) {

        int magColorResourceId;
        // switch cannot take double so we convert to int
        int magFloor = (int) Math.floor(mag);

        switch (magFloor) {
            case 0:
            case 1:
                magColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magColorResourceId = R.color.magnitude9;
                break;
            default:
                magColorResourceId = R.color.magnitude10;
                break;
        }
        return ContextCompat.getColor(getContext(), magColorResourceId);
    }
}
