package com.example.travelpad.viewmodels.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.models.Travel;
import com.example.travelpad.repositories.HotelRepository;
import com.example.travelpad.repositories.ItemRepository;
import com.example.travelpad.repositories.TransportationRepository;
import com.example.travelpad.repositories.TravelRepository;

import java.util.List;

public class TravelListViewModel extends AndroidViewModel {
    private TravelRepository repository;
    private HotelRepository hotelRepository;
    private ItemRepository itemRepository;
    private TransportationRepository transportationRepository;

    public TravelListViewModel(@NonNull Application application) {
        super(application);
        repository = TravelRepository.getInstance(application);
        hotelRepository = HotelRepository.getInstance(application);
        itemRepository = ItemRepository.getInstance(application);
        transportationRepository = TransportationRepository.getInstance(application);
    }

    public LiveData<List<Travel>> getTravels(){
        return repository.getAllTravels();
    }

    public void removeTravel(Travel travel){
        hotelRepository.removeHotelsInTravel(travel.getId());
        transportationRepository.removeTransportationInTravel(travel.getId());
        itemRepository.removeItemsInTravel(travel.getId());
        repository.removeTravel(travel);
    }


}
