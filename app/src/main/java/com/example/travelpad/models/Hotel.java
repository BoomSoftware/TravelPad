package com.example.travelpad.models;

import android.location.Location;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hotel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int travelId;
    private String name;
    private int stayDays;
    private int pricePerDay;
    private double latitude;
    private double altitude;

    public Hotel(int travelId, String name, int stayDays, int pricePerDay, double latitude, double altitude) {
        this.travelId = travelId;
        this.name = name;
        this.stayDays = stayDays;
        this.pricePerDay = pricePerDay;
        this.latitude = latitude;
        this.altitude = altitude;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
