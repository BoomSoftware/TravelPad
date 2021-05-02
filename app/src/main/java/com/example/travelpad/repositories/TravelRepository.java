package com.example.travelpad.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.travelpad.data.TravelDAO;
import com.example.travelpad.data.TravelDatabase;
import com.example.travelpad.models.Travel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TravelRepository {
    private static TravelRepository instance;
    private TravelDAO travelDAO;
    private ExecutorService executorService;

    private TravelRepository(Application application) {
        TravelDatabase database = TravelDatabase.getInstance(application);
        travelDAO = database.travelDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized TravelRepository getInstance(Application application) {
        if (instance == null) {
            instance = new TravelRepository(application);
        }
        return instance;
    }

    public void createTravel(Travel travel) {
        executorService.execute(() -> travelDAO.createTravel(travel));
    }

    public LiveData<List<Travel>> getAllTravels() {
       return travelDAO.getAllTravels();
    }

    public LiveData<Travel> getTravelByID(int id) {
        return travelDAO.getTravelById(id);
    }

    public void removeTravel(Travel travel) {
        executorService.execute(() -> travelDAO.removeTravel(travel));
    }
}
