package com.example.travelpad.viewmodels.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.models.Travel;
import com.example.travelpad.repositories.TravelRepository;

import java.util.List;

public class TravelListViewModel extends AndroidViewModel {
    private TravelRepository repository;

    public TravelListViewModel(@NonNull Application application) {
        super(application);
        repository = TravelRepository.getInstance(application);
    }

    public LiveData<List<Travel>> getTravels(){
        return repository.getAllTravels();
    }


}
