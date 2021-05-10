package com.example.travelpad.viewmodels.travel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.models.Hotel;
import com.example.travelpad.repositories.HotelRepository;

import java.util.List;

public class HotelListViewModel extends AndroidViewModel {
    private HotelRepository hotelRepository;

    public HotelListViewModel(Application application){
        super(application);
        hotelRepository = HotelRepository.getInstance(application);
    }

    public LiveData<List<Hotel>> getHotelsForTravel(int travelId){
        return hotelRepository.getHotelForTravel(travelId);
    }

    public void deleteHotel(int placeId) {
        hotelRepository.deleteHotelFromTravel(placeId);
    }
}
