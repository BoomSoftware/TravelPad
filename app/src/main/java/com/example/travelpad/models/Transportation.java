package com.example.travelpad.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Transportation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int travelId;
    private boolean isTicketBought;
    private String direction;
    private String type;
    private String startingDate;

    private int durationHour;
    private int durationMinute;
    private double price;

    private String destinationPointName;
    private String startingPointName;

    private String ticketPath;

    private int startingHour;
    private int startingMinute;

    public Transportation(int travelId, int durationHour, int durationMinute, double price, String destinationPointName, String startingPointName, String startingDate, int startingHour, int startingMinute, String direction, String type) {
        this.durationHour = durationHour;
        this.durationMinute = durationMinute;
        this.price = price;
        this.travelId = travelId;
        this.destinationPointName = destinationPointName;
        this.startingPointName = startingPointName;
        this.startingHour = startingHour;
        this.startingMinute = startingMinute;
        this.direction = direction;
        this.isTicketBought = false;
        this.startingDate = startingDate;
        this.type = type;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isTicketBought() {
        return isTicketBought;
    }

    public void setTicketBought(boolean ticketBought) {
        isTicketBought = ticketBought;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public int getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(int durationHour) {
        this.durationHour = durationHour;
    }

    public int getDurationMinute() {
        return durationMinute;
    }

    public void setDurationMinute(int durationMinute) {
        this.durationMinute = durationMinute;
    }

    public String getTicketPath() {
        return ticketPath;
    }

    public void setTicketPath(String ticketPath) {
        this.ticketPath = ticketPath;
    }
}
