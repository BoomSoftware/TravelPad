package com.example.travelpad.viewmodels.travel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.travelpad.models.Hotel;
import com.example.travelpad.repositories.HotelRepository;

public class AddHotelViewModel extends AndroidViewModel {

    private HotelRepository hotelRepository;

    public AddHotelViewModel(@NonNull Application application) {
        super(application);
        hotelRepository = HotelRepository.getInstance(application);
    }

    public void addNewHotel(Hotel hotel){
        hotelRepository.addHotelToTravel(hotel);
    }
}
