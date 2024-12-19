plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true // Включаем поддержку Compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0" // Версия для компилятора Compose
        kotlinCompilerVersion = "1.8.10" // Убедитесь, что используете правильную версию Kotlin
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
}

dependencies {
    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.4.0") // Основной UI Compose
    implementation("androidx.compose.material3:material3:1.1.0") // Material3 компоненты для Compose
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0") // Для превью Compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0") // Для работы с жизненным циклом
    implementation("androidx.activity:activity-compose:1.6.0") // Для интеграции с активностями

    // Для работы с корутинами
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Для работы с Coil в Compose (загрузка изображений)
    implementation("io.coil-kt:coil-compose:2.1.0")

    // Для Unit тестов и UI тестов
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.0") // UI тесты с JUnit
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.0") // Инструменты для разработки и отладки

    // Для тестов
    testImplementation("junit:junit:4.13.2") // JUnit для юнит-тестов
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // JUnit для Android тестов
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Для UI тестов с Espresso
}
