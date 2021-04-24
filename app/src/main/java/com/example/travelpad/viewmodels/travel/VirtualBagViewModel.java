package com.example.travelpad.viewmodels.travel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.models.Item;
import com.example.travelpad.repositories.ItemRepository;

import java.util.List;

public class VirtualBagViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;

    public VirtualBagViewModel(@NonNull Application application) {
        super(application);
        itemRepository = ItemRepository.getInstance(application);
    }

    public void addItemToVirtualBag(Item item) {
        itemRepository.addItemToVirtualBag(item);
    }

    public void removeItemFromVirtualBag(Item item) {
        itemRepository.removeItemFromVirtualBag(item);
    }

    public LiveData<List<Item>> getVirtualBagForTravel(int travelID) {
        return itemRepository.getItemsForTravel(travelID);
    }

    public void updateItemStatus(int itemID, boolean status) {
        itemRepository.updateItemStatus(itemID, status);
    }
}
