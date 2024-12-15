package com.example.newcat2.data.api;

import com.example.newcat2.data.model.FilterRequest;
import com.example.newcat2.data.model.FoodResponse;
import com.example.newcat2.data.model.ShopResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/v1/feed/all")
    Call<FoodResponse> getAllFoodItems(@Body FilterRequest filterRequest);

    @GET("api/v1/shops/feed/{id}")
    Call<ShopResponse> getShopById(@Path("id") int id); // New method for GET request
}