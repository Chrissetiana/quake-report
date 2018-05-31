package com.chrissetiana.quakereport;

public class Earthquake {

    private String magnitude;
    private String location;
    private long time;

    public Earthquake(String mag, String loc, long millisec) {
        magnitude = mag;
        location = loc;
        time = millisec;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }
}
