package com.example.travelpad.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.travelpad.data.TransportationDAO;
import com.example.travelpad.data.TravelDatabase;
import com.example.travelpad.models.Transportation;
import com.example.travelpad.models.TransportationDirections;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransportationRepository {
    private static TransportationRepository instance;
    private TransportationDAO transportationDAO;
    private ExecutorService executorService;

    private TransportationRepository(Application application) {
        TravelDatabase database = TravelDatabase.getInstance(application);
        transportationDAO = database.transportationDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized TransportationRepository getInstance(Application application) {
        if(instance == null) {
            instance = new TransportationRepository(application);
        }
        return instance;
    }

    public void addTransportationToTravel(Transportation transportation) {
        executorService.execute(() -> transportationDAO.addTransportation(transportation));
    }

    public void deleteTransportationFromTravel(Transportation transportation) {
        executorService.execute(() -> transportationDAO.deleteTransportation(transportation));
    }

    public LiveData<List<Transportation>> getTransportationForTravel(int transportationID, TransportationDirections direction){
        return transportationDAO.getTransportationForTravel(transportationID, direction.toString());
    }

    public void updateIsBoughtStatus(int transportationID, boolean status) {
        executorService.execute(() -> transportationDAO.updateIsBoughtStatus(transportationID, status));
    }

    public void updateTicketPath(int transportationID, String ticketPath) {
        executorService.execute(() -> transportationDAO.updateTicketPath(transportationID, ticketPath));
    }
}
