package com.example.travelpad.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Hotel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int travelId;
    private String name;
    private String address;
    private double rating;
    private int days;
    private float lat;
    private float lng;
    private String photoUrl;
    private String phoneNo;
    private double pricePerDay;
    private String reservationPath;

    @Ignore
    public Hotel(int travelId, int days, double pricePerDay){
        this.travelId = travelId;
        this.days = days;
        this.pricePerDay = pricePerDay;
    }

    public Hotel(int travelId, String name, String address, double rating, int days, float lat, float lng, String photoUrl, String phoneNo, double pricePerDay, String reservationPath) {
        this.travelId = travelId;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.days = days;
        this.lat = lat;
        this.lng = lng;
        this.photoUrl = photoUrl;
        this.phoneNo = phoneNo;
        this.pricePerDay = pricePerDay;
        this.reservationPath = reservationPath;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}