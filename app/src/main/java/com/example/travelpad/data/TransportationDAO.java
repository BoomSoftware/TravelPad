package com.example.travelpad.data;

public class TransportationDAO {
    private static TransportationDAO instance;

    public static TransportationDAO getInstance(){
        if(instance == null) {
            instance = new TransportationDAO();
        }
        return instance;
    }
}
