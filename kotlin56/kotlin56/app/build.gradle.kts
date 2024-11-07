plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.kotlin5_6"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlin5_6"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

}

dependencies {
    implementation ("io.insert-koin:koin-android:3.3.0")
    implementation ("io.insert-koin:koin-android-compat:3.3.0")
    implementation("androidx.room:room-runtime:2.4.2")
    kapt("androidx.room:room-compiler:2.4.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:x.x.x")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
