package com.example.kotlin5_6.di

import android.app.Application
import com.example.kotlin5_6.database.AppDatabase
import com.example.kotlin5_6.database.TodoDao
import com.example.kotlin5_6.retrofit.TodoApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    single {
        // Room database instance
        AppDatabase.getDatabase(get())
    }

    single { get<AppDatabase>().todoDao() }
}

val apiModule = module {
    single {
        // Retrofit instance for TodoApi
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }
}

