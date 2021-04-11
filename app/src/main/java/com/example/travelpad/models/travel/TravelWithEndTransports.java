package com.example.travelpad.models.travel;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.travelpad.models.Item;
import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.Travel;

import java.util.List;

public class TravelWithEndTransports {
    @Embedded
    public Travel travel;

    @Relation(
            parentColumn = "id",
            entityColumn = "travelId"
    )
    private List<Transportation> endTransports;

    public List<Transportation> getEndTransports() {
        return endTransports;
    }

    public void setEndTransports(List<Transportation> endTransports) {
        this.endTransports = endTransports;
    }
}
