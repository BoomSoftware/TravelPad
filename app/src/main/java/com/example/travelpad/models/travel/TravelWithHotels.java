package com.example.travelpad.models.travel;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.travelpad.models.Hotel;
import com.example.travelpad.models.Travel;

import java.util.List;

public class TravelWithHotels {
    @Embedded
    public Travel travel;
    @Relation(
            parentColumn = "id",
            entityColumn = "travelId"
    )
    private List<Hotel>hotels;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
