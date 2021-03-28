package com.example.travelpad.models;

public class Transportation {
    private int duration;
    private double price;

    private String destinationPointName;
    private String startingPointName;

    private int startingHour;
    private int startingMinute;

    private int endingHour;
    private int endingMinute;

    //ticket photos

    public Transportation(int duration, double price, String destinationPointName, String startingPointName, int startingHour, int startingMinute, int endingHour, int endingMinute) {
        this.duration = duration;
        this.price = price;
        this.destinationPointName = destinationPointName;
        this.startingPointName = startingPointName;
        this.startingHour = startingHour;
        this.startingMinute = startingMinute;
        this.endingHour = endingHour;
        this.endingMinute = endingMinute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDestinationPointName() {
        return destinationPointName;
    }

    public void setDestinationPointName(String destinationPointName) {
        this.destinationPointName = destinationPointName;
    }

    public String getStartingPointName() {
        return startingPointName;
    }

    public void setStartingPointName(String startingPointName) {
        this.startingPointName = startingPointName;
    }

    public int getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(int startingHour) {
        this.startingHour = startingHour;
    }

    public int getStartingMinute() {
        return startingMinute;
    }

    public void setStartingMinute(int startingMinute) {
        this.startingMinute = startingMinute;
    }

    public int getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(int endingHour) {
        this.endingHour = endingHour;
    }

    public int getEndingMinute() {
        return endingMinute;
    }

    public void setEndingMinute(int endingMinute) {
        this.endingMinute = endingMinute;
    }
}
