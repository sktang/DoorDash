package com.doordash.kitty.doordash.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v2/restaurant")
    Call<List<Restaurant>> getAllRestaurants(
            @Query("lat") Double lat,
            @Query("lng") Double lng,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("v2/restaurant/{id}")
    Call<Restaurant> getRestaurant(
            @Path("id") long id);

}
