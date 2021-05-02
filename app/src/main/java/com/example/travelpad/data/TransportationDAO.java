package com.example.travelpad.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.TransportationDirections;

import java.util.List;

@Dao
public interface TransportationDAO {
    @Insert
    void addTransportation(Transportation transportation);

    @Delete
    void deleteTransportation(Transportation transportation);

    @Query("SELECT * FROM Transportation WHERE travelId=:travelID AND direction=:direction")
    LiveData<List<Transportation>> getTransportationForTravel(int travelID, String direction);

    @Query("UPDATE TRANSPORTATION SET ticketPath=:ticketPath WHERE id=:transportationID")
    void updateTicketPath(int transportationID, String ticketPath);

    @Query("SELECT COUNT(id) FROM Transportation WHERE ticketPath IS NULL AND travelId=:travelId")
    LiveData<Integer> getTransportationWithoutTicket(int travelId);

    @Query("SELECT SUM(price) FROM Transportation WHERE travelId=:travelId")
    LiveData<Double> getTransportTotalPrice(int travelId);

    @Query("DELETE FROM Transportation WHERE travelId=:travelId")
    void removeTransportationInTravel(int travelId);
}
