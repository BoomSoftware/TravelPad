package com.example.travelpad.data;

public class HotelDAO {
    private static HotelDAO instance;

    public static HotelDAO getInstance(){
        if(instance == null) {
            instance = new HotelDAO();
        }
        return instance;
    }
}
