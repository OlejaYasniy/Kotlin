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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Для Android тестов
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Для JUnit в Android
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Для UI тестов с Espresso
    androidTestImplementation("androidx.test:core:1.5.0") // Для core тестирования Android
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4") // Для тестирования корутин
    androidTestImplementation("androidx.test:rules:1.5.0") // Для правил тестирования

    // Для JUnit (для использования assertNotNull, assertTrue и т.д. в тестах)
    testImplementation("junit:junit:4.13.2") // JUnit для юнит-тестов

    // Для работы с корутинами
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Robolectric для тестирования Android компонентов без устройства
    testImplementation("org.robolectric:robolectric:4.9") // Для тестирования Android компоненто

    // Coil (Библиотека для загрузки изображений)
    implementation("io.coil-kt:coil:2.1.0") // Загрузка изображений

    // AndroidX
    implementation("androidx.core:core-ktx:1.12.0") // Короткие расширения для работы с Android API
    implementation("androidx.appcompat:appcompat:1.6.1") // Поддержка совместимости с различными версиями Android
    implementation("com.google.android.material:material:1.9.0") // Material Design компоненты
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // ConstraintLayout для UI

    // Kotlin Test для использования assertNotNull, assertTrue
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10") // Для Kotlin assertions
}

