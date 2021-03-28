package com.example.travelpad.models;

import android.location.Location;

public class Hotel {
    private String name;
    private int stayDays;
    private int pricePerDay;
    private Location location;

    public Hotel(String name, int stayDays, int pricePerDay, Location location) {
        this.name = name;
        this.stayDays = stayDays;
        this.pricePerDay = pricePerDay;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStayDays() {
        return stayDays;
    }

    public void setStayDays(int stayDays) {
        this.stayDays = stayDays;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
