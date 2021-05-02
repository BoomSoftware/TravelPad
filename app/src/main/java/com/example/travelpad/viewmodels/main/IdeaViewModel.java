package com.example.travelpad.viewmodels.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.travelpad.models.IdeaResponse;
import com.example.travelpad.repositories.IdeaRepository;

public class IdeaViewModel extends ViewModel {
    private IdeaRepository ideaRepository;

    public IdeaViewModel(){
        ideaRepository = IdeaRepository.getInstance();
    }

    public LiveData<IdeaResponse.Result> getRandomPlace(){
        return ideaRepository.getRandomPlace();
    }

    public void searchForRandomPlace(String type,String apiKey){
        ideaRepository.generateRandomPlace(type, apiKey);
    }
}
