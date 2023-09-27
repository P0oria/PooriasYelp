package com.pooria.pooriasyelp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    static String BASE_URL = "https://api.yelp.com/v3/";

    @GET("businesses/search")
    Call<DataResponse> getRestaurant(@Query("term") String term,
                                     @Query("location") String location,
                                     @Query("limit") int limit);
}
