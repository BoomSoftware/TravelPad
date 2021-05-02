package com.example.travelpad.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hotel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int travelId;
    private String placeId;
    private int days;
    private double pricePerDay;
    private String reservationPath;


    public Hotel(int travelId, String placeId, int days, double pricePerDay) {
        this.travelId = travelId;
        this.placeId = placeId;
        this.days = days;
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

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getReservationPath() {
        return reservationPath;
    }

    public void setReservationPath(String reservationPath) {
        this.reservationPath = reservationPath;
    }
}
