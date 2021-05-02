package com.example.travelpad.networking;
import com.example.travelpad.models.IdeaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IdeaAPI {
    @GET("maps/api/place/textsearch/json")
    Call<IdeaResponse> getPlaceById(@Query("key") String apiKey, @Query("type") String type);
}
