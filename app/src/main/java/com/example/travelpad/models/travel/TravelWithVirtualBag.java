package com.example.travelpad.models.travel;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.example.travelpad.models.Item;
import com.example.travelpad.models.Travel;
import java.util.List;

public class TravelWithVirtualBag {
    @Embedded public Travel travel;
    @Relation(
            parentColumn = "id",
            entityColumn = "travelId"
    )
    private List<Item> virtualBag;

    public List<Item> getVirtualBag() {
        return virtualBag;
    }

    public void setVirtualBag(List<Item> virtualBag) {
        this.virtualBag = virtualBag;
    }
}
