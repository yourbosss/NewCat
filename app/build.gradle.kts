plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}



android {
    namespace = "com.example.newcat2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newcat2"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}



dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui) // Исправлено
    implementation(libs.androidx.ui.graphics) // Исправлено
    implementation(libs.androidx.ui.tooling.preview) // Исправлено
    implementation(libs.androidx.material3)
    implementation(libs.material)
    implementation(libs.androidx.drawerlayout)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4) // Исправлено
    debugImplementation(libs.androidx.ui.tooling) // Исправлено
    debugImplementation(libs.androidx.ui.test.manifest) // Исправлено

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("androidx.recyclerview:recyclerview:1.2.1")


    // Облегченная библиотека, содержит только карту, слой пробок,
    // LocationManager, UserLocationLayer
    // и возможность скачивать офлайн-карты (только в платной версии).
//    implementation("com.yandex.android:maps.mobile:4.9.0-lite")

    // Полная библиотека в дополнение к lite версии предоставляет автомобильную маршрутизацию,
    // веломаршрутизацию, пешеходную маршрутизацию и маршрутизацию на общественном транспорте,
    // поиск, suggest, геокодирование и отображение панорам.
    implementation("com.yandex.android:maps.mobile:4.9.0-full")
//    implementation ("com.yandex.android:mapkit:4.0.0")
}
