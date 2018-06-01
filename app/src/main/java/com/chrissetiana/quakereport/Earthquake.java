package com.chrissetiana.quakereport;

public class Earthquake {

    private double magnitude;
    private String location;
    private long time;

    public Earthquake(double mag, String loc, long millisec) {
        magnitude = mag;
        location = loc;
        time = millisec;
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
}
