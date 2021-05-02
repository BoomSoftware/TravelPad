package com.example.travelpad.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.travelpad.data.HotelDAO;
import com.example.travelpad.data.TravelDatabase;
import com.example.travelpad.models.Hotel;
import com.example.travelpad.models.HotelResponse;
import com.example.travelpad.networking.HotelAPI;
import com.example.travelpad.networking.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelRepository {

    private static HotelRepository instance;
    private HotelDAO hotelDAO;
    private ExecutorService executorService;
    private MutableLiveData<List<HotelResponse>> hotels;
    private List<HotelResponse> hotelResponses;

    public HotelRepository(Application application){
        TravelDatabase database = TravelDatabase.getInstance(application);
        hotelDAO = database.hotelDAO();
        hotels = new MutableLiveData<>();
        hotelResponses = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HotelRepository getInstance(Application application) {
        if(instance == null) {
            instance = new HotelRepository(application);
        }
        return instance;
    }

    public void addHotelToTravel(Hotel hotel) {
        executorService.execute(() -> hotelDAO.addHotel(hotel));
    }

    public void updateReservationPath(String placeId, String reservationPath){
        executorService.execute(() -> hotelDAO.updateReservationPath(placeId, reservationPath));
    }

    public LiveData<List<Hotel>> getHotelForTravel(int transportationID){
        return hotelDAO.getHotelForTravel(transportationID);
    }

    public void deleteHotelFromTravel(String placeId) {
        executorService.execute(() -> hotelDAO.removeHotel(placeId));
    }

    public LiveData<List<HotelResponse>> getHotels() {
        return hotels;
    }

    public LiveData<Integer> getHotelsWithoutReservation(int travelId) {
        return hotelDAO.getHotelsWithoutReservation(travelId);
    }

    public void searchForHotel(String apiKey, Hotel hotel){
        hotelResponses = new ArrayList<>();
        HotelAPI hotelAPI = ServiceGenerator.getHotelApi();
        Call<HotelResponse> call = hotelAPI.getPlaceById(apiKey, hotel.getPlaceId());
        call.enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(Call<HotelResponse> call, Response<HotelResponse> response) {
                if(response.isSuccessful()) {
                   HotelResponse hotelResponse = response.body();
                   hotelResponse.setDays(hotel.getDays());
                   hotelResponse.setPricePerDay(hotel.getPricePerDay());
                   hotelResponse.setReservationPath(hotel.getReservationPath());
                   hotelResponse.setPlaceId(hotel.getPlaceId());

                   hotelResponses.add(hotelResponse);
                   hotels.postValue(hotelResponses);
                }
            }

            @Override
            public void onFailure(Call<HotelResponse> call, Throwable t) {}
        });
    }

    public void removeHotelsInTravel(int id) {
       executorService.execute(() -> hotelDAO.removeHotelsInTravel(id));
    }
}
