package com.example.travelpad.data;

public class VirtualBagDAO {
    private static VirtualBagDAO instance;

    public static VirtualBagDAO getInstance(){
        if(instance == null) {
            instance = new VirtualBagDAO();
        }
        return instance;
    }
}
