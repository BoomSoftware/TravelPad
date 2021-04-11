package com.example.travelpad.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.travelpad.models.Travel;
import com.example.travelpad.repositories.TravelRepository;

public class NewTravelViewModel extends AndroidViewModel {

    private TravelRepository repository;

    public NewTravelViewModel(Application application){
        super(application);
        repository = TravelRepository.getInstance(application);
    }

    public void createNewTravel(Travel travel){
        repository.createTravel(travel);
    }
}
