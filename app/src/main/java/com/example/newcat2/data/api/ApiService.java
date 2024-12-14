package com.example.newcat2.data.api;

import com.example.newcat2.data.model.FilterRequest;
import com.example.newcat2.data.model.FoodResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/v1/feed/all")
    Call<FoodResponse> getAllFoodItems(@Body FilterRequest filterRequest);
}