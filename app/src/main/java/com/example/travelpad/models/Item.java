package com.example.travelpad.models;

public class Item {
    private String name;
    private boolean isPacked;

    public Item(String name) {
        isPacked = false;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
