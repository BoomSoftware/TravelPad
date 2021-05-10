package com.example.travelpad.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.repositories.HotelRepository;
import com.example.travelpad.repositories.TransportationRepository;
import com.example.travelpad.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class TravelActivityViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private TransportationRepository transportationRepository;
    private HotelRepository hotelRepository;

    public TravelActivityViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        hotelRepository = HotelRepository.getInstance(application);
        transportationRepository = TransportationRepository.getInstance(application);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void updateTicketPath(int transportationId, String ticketPath){
        transportationRepository.updateTicketPath(transportationId, ticketPath);
    }

    public void updateReservationPath(int placeId, String reservationPath){
        hotelRepository.updateReservationPath(placeId, reservationPath);
    }

    public void signOut(){
        userRepository.signOut();
    }
}
