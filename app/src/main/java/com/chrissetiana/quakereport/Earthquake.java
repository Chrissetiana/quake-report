package com.chrissetiana.quakereport;

import android.os.AsyncTask;

import java.util.List;

public class Earthquake {

    private double magnitude;
    private String location;
    private long time;
    private String url;

    public Earthquake(double mag, String loc, long millisec, String web) {
        magnitude = mag;
        location = loc;
        time = millisec;
        url = web;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

}
