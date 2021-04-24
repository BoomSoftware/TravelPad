package com.example.travelpad.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.travelpad.models.Hotel;
import com.example.travelpad.models.Item;
import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.Travel;

@Database(entities = {Travel.class, Transportation.class, Item.class, Hotel.class}, version = 7)
public abstract class TravelDatabase extends RoomDatabase {
    private static TravelDatabase instance;
    public abstract TravelDAO travelDAO();
    public abstract ItemDAO itemDAO();
    public abstract TransportationDAO transportationDAO();

    public static synchronized TravelDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TravelDatabase.class, "travel_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
