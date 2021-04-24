package com.example.travelpad.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.travelpad.data.ItemDAO;
import com.example.travelpad.data.TravelDatabase;
import com.example.travelpad.models.Item;
import com.example.travelpad.models.Travel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemRepository {
    private static ItemRepository instance;
    private ItemDAO itemDAO;
    private ExecutorService executorService;

    private ItemRepository(Application application) {
        TravelDatabase database = TravelDatabase.getInstance(application);
        itemDAO = database.itemDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized ItemRepository getInstance(Application application) {
        if (instance == null)  {
            instance = new ItemRepository(application);
        }
        return instance;
    }

    public void addItemToVirtualBag(Item item) {
        executorService.execute(() -> itemDAO.addItem(item));
    }

    public void removeItemFromVirtualBag(Item item) {
       executorService.execute(() -> itemDAO.removeItem(item));
    }

    public void updateItemStatus(int itemId, boolean status){
        executorService.execute(() -> itemDAO.changeItemPackedStatus(itemId, status));
    }

    public LiveData<List<Item>> getItemsForTravel(int travelID) {
       return itemDAO.getVirtualBagForTravel(travelID);
    }
}
