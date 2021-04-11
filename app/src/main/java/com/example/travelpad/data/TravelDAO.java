package com.example.travelpad.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.travelpad.models.Travel;
import com.example.travelpad.models.travel.TravelWithVirtualBag;

import java.util.List;

@Dao
public interface TravelDAO {
    @Insert
    void createTravel(Travel travel);

    @Transaction
    @Query("SELECT * FROM Travel")
    LiveData<List<Travel>> getAllTravels();
}
