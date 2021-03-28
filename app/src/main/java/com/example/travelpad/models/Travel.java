package com.example.travelpad.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Travel {
    private int id;
    private String name;
    private ArrayList<Item> virtualTravelBag;
    private ArrayList<Hotel> hotels;

    private LocalDate startDate;
    private LocalDate endDate;

    private ArrayList<Transportation> destinationRoadTransports;
    private ArrayList<Transportation> wayBackTransports;

    public Travel(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinationRoadTransports = new ArrayList<>();
        this.wayBackTransports = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.virtualTravelBag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getVirtualTravelBag() {
        return virtualTravelBag;
    }

    public void setVirtualTravelBag(ArrayList<Item> virtualTravelBag) {
        this.virtualTravelBag = virtualTravelBag;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public ArrayList<Transportation> getDestinationRoadTransports() {
        return destinationRoadTransports;
    }

    public void setDestinationRoadTransports(ArrayList<Transportation> destinationRoadTransports) {
        this.destinationRoadTransports = destinationRoadTransports;
    }

    public ArrayList<Transportation> getWayBackTransports() {
        return wayBackTransports;
    }

    public void setWayBackTransports(ArrayList<Transportation> wayBackTransports) {
        this.wayBackTransports = wayBackTransports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
