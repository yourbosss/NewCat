package com.example.newcat2.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newcat2.R;
import com.example.newcat2.data.api.ApiService;
import com.example.newcat2.data.model.FilterRequest;
import com.example.newcat2.data.model.FoodItem;
import com.example.newcat2.data.model.FoodResponse;
import com.example.newcat2.ui.FoodAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainScene extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<FoodItem> foodItemViews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview); //МАКЕТ РЕСАЙКЛ ВЬЮ.

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recycler_view); // Исправлено: присваиваем результат findViewById переменной recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodAdapter(foodItemViews);
        recyclerView.setAdapter(adapter);

        fetchFoodItems();
    }

    private void fetchFoodItems() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        String adultString = getIntent().getStringExtra("selected_age_category");
        String premiumString = getIntent().getStringExtra("selected_budget_premium");
        String sterilizedString = getIntent().getStringExtra("selected_sterilized_regular");
        String wetFoodString = getIntent().getStringExtra("selected_type_food");

        FilterRequest filterRequest = getFilterRequest(adultString, premiumString, sterilizedString, wetFoodString);

        Log.e("MainScene", "Данные запроса: " + filterRequest);

        Call<FoodResponse> call = apiService.getAllFoodItems(filterRequest);

        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("MainScene", "Ответ от сервера: " + response.body());
                    if (response.body().getData() != null) {
                        foodItemViews.clear();
                        foodItemViews.addAll(response.body().getData());
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e("MainScene", "Полученные данные пусты (data: null).");
                    }
                } else {
                    Log.e("MainScene", "Ошибка в ответе: " + response.code() + " " + response.message());
                    if (response.errorBody() != null) {
                        try {
                            Log.e("MainScene", "Ошибка тела: " + response.errorBody().string());
                        } catch (IOException e) {
                            Log.e("MainScene", "Ошибка чтения тела ошибки: " + e.getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Log.e("MainScene", "Ошибка запроса: " + t.getMessage());
            }
        });
    }

    @NonNull
    private static FilterRequest getFilterRequest(String adultString, String premiumString, String sterilizedString, String wetFoodString) {
        int isAdult = "Взрослый кот".equals(adultString) ? 2 : 1;
        int isPremium = "Премиум".equals(premiumString) ? 2 : 1;
        int isSterilized = "Стерилизованный".equals(sterilizedString) ? 2 : 1;
        int isWetFood = "Влажный корм".equals(wetFoodString) ? 2 : 1;

        return new FilterRequest(isAdult, isPremium, isSterilized, isWetFood);
    }


}



