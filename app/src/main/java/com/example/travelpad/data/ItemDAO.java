package com.example.travelpad.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.travelpad.models.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void addItem(Item item);

    @Delete
    void removeItem(Item item);

    @Transaction
    @Query("SELECT * FROM Item WHERE travelId=:travelID")
    LiveData<List<Item>> getVirtualBagForTravel(int travelID);

    @Query("UPDATE Item SET isPacked=:status WHERE id=:itemId")
    void changeItemPackedStatus(int itemId, boolean status);

    @Query("SELECT COUNT(id) FROM Item WHERE isPacked=0 AND travelId=:travelId")
    LiveData<Integer> getNotPackedItems(int travelId);

    @Query("DELETE FROM Item WHERE travelId=:travelId")
    void removeItemsInTravel(int travelId);
}
