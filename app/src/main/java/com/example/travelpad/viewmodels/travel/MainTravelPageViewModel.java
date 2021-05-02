package com.example.travelpad.viewmodels.travel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.models.Hotel;
import com.example.travelpad.models.HotelResponse;
import com.example.travelpad.models.Travel;
import com.example.travelpad.repositories.HotelRepository;
import com.example.travelpad.repositories.ItemRepository;
import com.example.travelpad.repositories.TransportationRepository;
import com.example.travelpad.repositories.TravelRepository;

import java.util.List;

public class MainTravelPageViewModel extends AndroidViewModel {

    private TravelRepository travelRepository;
    private HotelRepository hotelRepository;
    private TransportationRepository transportationRepository;
    private ItemRepository itemRepository;

    public MainTravelPageViewModel(@NonNull Application application){
        super(application);
        travelRepository = TravelRepository.getInstance(application);
        hotelRepository = HotelRepository.getInstance(application);
        transportationRepository = TransportationRepository.getInstance(application);
        itemRepository = ItemRepository.getInstance(application);
    }

    public LiveData<Travel> getTravelByID(int id){
        return travelRepository.getTravelByID(id);
    }

    public LiveData<Integer> getHotelsWithoutReservation(int travelId){
        return hotelRepository.getHotelsWithoutReservation(travelId);
    }

    public LiveData<Integer> getTransportationWithoutTicket(int travelId){
        return transportationRepository.getTransportationWithoutTicket(travelId);
    }

    public LiveData<Integer> getNotPackedItems(int travelId){
        return itemRepository.getNotPackedItems(travelId);
    }

    public LiveData<Double> getTransportTotalPrice(int travelId){
        return transportationRepository.getTransportTotalPrice(travelId);
    }

    public LiveData<List<Hotel>> getHotels(int travelId){
        return hotelRepository.getHotelForTravel(travelId);
    }
}
