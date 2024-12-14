package com.example.newcat2.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newcat2.R;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MapActivity extends AppCompatActivity {

    private MapView mapView; // Объявляем переменную для MapView

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
        double latitude = getIntent().getDoubleExtra("latitude", 55.3964); // Широта Кемерово по умолчанию
        double longitude = getIntent().getDoubleExtra("longitude", 86.0859); // Долгота Кемерово по умолчанию

        // Создаем CameraPosition с нужными параметрами
        CameraPosition cameraPosition = new CameraPosition(
                new Point(latitude, longitude), // Указываем координаты
                15f, // Уровень зума
                0f, // Азимут
                0f  // Наклон
        );

        // Перемещаем карту на указанные координаты
        mapView.getMap().move(cameraPosition); // Используем объект CameraPosition
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
