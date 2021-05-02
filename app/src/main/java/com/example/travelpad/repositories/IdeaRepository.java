package com.example.travelpad.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.travelpad.models.HotelResponse;
import com.example.travelpad.models.IdeaResponse;
import com.example.travelpad.networking.HotelAPI;
import com.example.travelpad.networking.IdeaAPI;
import com.example.travelpad.networking.ServiceGenerator;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdeaRepository {
    private static IdeaRepository instance;
    private MutableLiveData<IdeaResponse.Result> randomPlace;

    public IdeaRepository(){
        randomPlace = new MutableLiveData<>();
    }

    public static synchronized IdeaRepository getInstance(){
        if(instance == null){
            instance = new IdeaRepository();
        }
        return instance;
    }

    public LiveData<IdeaResponse.Result> getRandomPlace(){
        return randomPlace;
    }

    public void generateRandomPlace(String type, String apiKey){
        IdeaAPI ideaAPI = ServiceGenerator.getIdeaAPI();
        Call<IdeaResponse> call = ideaAPI.getPlaceById(apiKey, type);
        call.enqueue(new Callback<IdeaResponse>() {
            @Override
            public void onResponse(Call<IdeaResponse> call, Response<IdeaResponse> response) {
                if(response.isSuccessful()){
                    IdeaResponse ideaResponse = response.body();
                    Random random = new Random();
                    IdeaResponse.Result result = ideaResponse.getResults().get(random.nextInt(ideaResponse.getResults().size()));
                    randomPlace.postValue(result);
                }
            }

            @Override
            public void onFailure(Call<IdeaResponse> call, Throwable t) {}
        });
    }
}
