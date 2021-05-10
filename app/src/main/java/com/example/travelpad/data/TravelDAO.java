package com.example.travelpad.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.travelpad.models.Travel;
import java.util.List;

@Dao
public interface TravelDAO {
    @Insert
    void createTravel(Travel travel);

    @Delete
    void removeTravel(Travel travel);

    @Transaction
    @Query("SELECT * FROM Travel")
    LiveData<List<Travel>> getAllTravels();

    @Transaction
    @Query("SELECT * FROM Travel WHERE id=:id")
    LiveData<Travel> getTravelById(int id);
}
