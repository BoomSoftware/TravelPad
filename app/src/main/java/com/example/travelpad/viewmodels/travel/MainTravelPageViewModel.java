package com.example.travelpad.viewmodels.travel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.travelpad.models.Travel;
import com.example.travelpad.repositories.TravelRepository;

public class MainTravelPageViewModel extends AndroidViewModel {

    private TravelRepository travelRepository;

    public MainTravelPageViewModel(@NonNull Application application){
        super(application);
        travelRepository = TravelRepository.getInstance(application);
    }

    public LiveData<Travel> getTravelByID(int id){
        return travelRepository.getTravelByID(id);
    }
}
