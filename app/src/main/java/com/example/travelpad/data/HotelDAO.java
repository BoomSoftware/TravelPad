package com.example.travelpad.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.travelpad.models.Hotel;

import java.util.List;

@Dao
public interface HotelDAO {
    @Insert
    void addHotel(Hotel hotel);

    @Query("DELETE FROM Hotel WHERE placeId=:placeId")
    void removeHotel(String placeId);

    @Transaction
    @Query("SELECT * FROM Hotel WHERE travelId=:travelID")
    LiveData<List<Hotel>> getHotelForTravel(int travelID);

    @Query("UPDATE HOTEL SET reservationPath=:reservationPath WHERE placeId=:placeId")
    void updateReservationPath(String placeId, String reservationPath);

    @Query("SELECT COUNT(id) FROM Hotel WHERE reservationPath IS NULL AND travelId=:travelId")
    LiveData<Integer> getHotelsWithoutReservation(int travelId);

    @Query("DELETE FROM Hotel WHERE travelId=:travelId")
    void removeHotelsInTravel(int travelId);
}
