package com.chrissetiana.quakereport;

public class Earthquake {

    private String magnitude;
    private String location;
    private String date;

    public Earthquake(String mag, String loc, String day) {
        magnitude = mag;
        location = loc;
        date = day;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
