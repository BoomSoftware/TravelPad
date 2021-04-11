package com.example.travelpad.models.travel;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.Travel;

import java.util.List;

public class TravelWithStartTransports {
    @Embedded
    public Travel travel;

    @Relation(
            parentColumn = "id",
            entityColumn = "travelId"
    )
    private List<Transportation> startTransports;

    public List<Transportation> getStartTransports() {
        return startTransports;
    }

    public void setStartTransports(List<Transportation> startTransports) {
        this.startTransports = startTransports;
    }
}
