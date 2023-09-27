package com.pooria.pooriasyelp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Interface for defining API endpoints and methods for making API requests.
public interface API {

    static String BASE_URL = "https://api.yelp.com/v3/";

    // Define an API endpoint to search for restaurants.
    @GET("businesses/search")
    Call<DataResponse> getRestaurant(@Query("term") String term,
                                     @Query("location") String location,
                                     @Query("limit") int limit);
}
