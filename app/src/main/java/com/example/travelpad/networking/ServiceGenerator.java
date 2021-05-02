package com.example.travelpad.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static HotelAPI hotelApi = retrofit.create(HotelAPI.class);
    private static IdeaAPI ideaAPI = retrofit.create(IdeaAPI.class);

    public static HotelAPI getHotelApi() { return hotelApi;}
    public static IdeaAPI getIdeaAPI() { return ideaAPI; }
}
