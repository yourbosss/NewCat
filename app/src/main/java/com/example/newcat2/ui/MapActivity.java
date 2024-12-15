package com.example.newcat2.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.newcat2.R;
import com.example.newcat2.data.api.ApiService;
import com.example.newcat2.data.model.ShopResponse;
import com.example.newcat2.ui.shop.ShopDetailActivity;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.TextStyle;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapActivity extends AppCompatActivity {

    private MapView mapView; // Объявляем переменную для MapView
    private static final String BASE_URL = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация MapKit
        MapKitFactory.setApiKey("7ba4c325-d140-432e-a660-e5bdca3812d7");
        MapKitFactory.initialize(this); // Инициализация должна быть здесь


        setContentView(R.layout.activity_map); // Убедитесь, что у вас есть соответствующий layout

        // Инициализация MapView
        mapView = findViewById(R.id.activity_map_xml);

        // Получаем координаты из Intent
        int id = getIntent().getIntExtra("id", 0); // Широта Кемерово по умолчанию

        // Создаем CameraPosition с нужными параметрами
        CameraPosition cameraPosition = new CameraPosition(
                new Point(55.3964, 86.0859), // Указываем координаты
                10f, // Уровень зума
                0f, // Азимут
                0f  // Наклон
        );

        // Перемещаем карту на указанные координаты
        mapView.getMap().move(cameraPosition); // Используем объект CameraPosition

        // Создаем Retrofit экземпляр
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Делаем API вызов
        getShopById(apiService, id); // Замените 1 на нужный ID магазина
    }
    private void getShopById(ApiService apiService, int id) {
        Call<ShopResponse> call = apiService.getShopById(id);
        call.enqueue(new Callback<ShopResponse>() {
            @Override
            public void onResponse(Call<ShopResponse> call, Response<ShopResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Обработка успешного ответа
                    List<ShopResponse.Shop> shops = response.body().getData();
                    for (ShopResponse.Shop shop : shops) {
                        // Добавляем маркер на карту для каждого магазина
                        addPlacemark(shop.getLatitude(), shop.getLongitude(), shop.getName(), shop.getId());
                    }
                } else {
                    Toast.makeText(MapActivity.this, "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShopResponse> call, Throwable t) {
                Log.e("MapActivity", "Ошибка: " + t.getMessage());
                Toast.makeText(MapActivity.this, "Ошибка сети", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addPlacemark(double latitude, double longitude, String name, int shopId) {
        MapObjectCollection mapObjects = mapView.getMap().getMapObjects();
        PlacemarkMapObject placemark = mapObjects.addPlacemark(new Point(latitude, longitude));
        // Load the original bitmap from resources
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.location);

        // Resize the bitmap to a maximum size
        Bitmap resizedBitmap = resizeBitmap(originalBitmap, 100, 100); // Set your max width and height

        // Set the resized bitmap as the icon
        placemark.setIcon(ImageProvider.fromBitmap(resizedBitmap));

        // Create a TextStyle for the placemark text
        TextStyle textStyle = new TextStyle();
        textStyle.setSize(14); // Set text size
        placemark.setText(name, textStyle); // Устанавливаем текст для маркера

        placemark.setUserData(name);
        placemark.addTapListener(new MapObjectTapListener() {
            @Override
            public boolean onMapObjectTap(MapObject mapObject, Point point) {
                // Handle the tap event
                String shopName = (String) mapObject.getUserData();
                Log.e("asdf","asdf");
                Intent intent = new Intent(MapActivity.this, ShopDetailActivity.class);
                intent.putExtra("shop_name", shopName); // Pass the shop name
                intent.putExtra("shop_id", shopId); // Pass the shop ID
                Toast.makeText(MapActivity.this, "Clicked on: " + shopName, Toast.LENGTH_SHORT).show();
                startActivity(intent); // Start the new activity
                return true; // Return true to indicate that the event was handled
            }
        });
    }
    private Bitmap resizeBitmap(Bitmap originalBitmap, int maxWidth, int maxHeight) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();

        // Calculate the scale factor
        float scale = Math.min((float) maxWidth / width, (float) maxHeight / height);

        // Calculate the new dimensions
        int newWidth = Math.round(scale * width);
        int newHeight = Math.round(scale * height);

        // Create a new bitmap with the new dimensions
        return Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
    }


    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart(); // Запуск MapKit
    }

    @Override
    protected void onStop() {
        super.onStop();
        MapKitFactory.getInstance().onStop(); // Остановка MapKit
    }
}
