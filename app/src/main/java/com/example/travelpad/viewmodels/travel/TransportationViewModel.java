package com.example.travelpad.viewmodels.travel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.TransportationDirections;
import com.example.travelpad.repositories.TransportationRepository;

import java.util.List;

public class TransportationViewModel extends AndroidViewModel {

    private TransportationRepository repository;

    public TransportationViewModel(@NonNull Application application) {
        super(application);
        repository = TransportationRepository.getInstance(application);
    }

    public LiveData<List<Transportation>> getTransportationForTravel(int travelID, TransportationDirections direction){
        return repository.getTransportationForTravel(travelID, direction);
    }

    public void addTransportationToTravel(Transportation transportation){
        repository.addTransportationToTravel(transportation);
    }

    public void deleteTransportationFromTravel(Transportation transportation) {
        repository.deleteTransportationFromTravel(transportation);
    }

    public void updateTicketPath(int transportationId, String ticketPath){
        repository.updateTicketPath(transportationId, ticketPath);
    }
}
