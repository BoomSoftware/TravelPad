package com.example.travelpad.networking;

import com.example.travelpad.models.HotelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HotelAPI {
    @GET("/maps/api/place/details/json")
    Call<HotelResponse>getPlaceById(@Query("key") String apiKey, @Query("placeid") String id);
}
